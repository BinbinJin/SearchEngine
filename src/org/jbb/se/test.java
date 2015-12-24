package org.jbb.se;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import kevin.zhang.NLPIR;

class test {

    public static void displayToken(String str, Analyzer analyzer) {
        try {
            // 将一个字符串创建成Token流
            TokenStream stream = analyzer.tokenStream("", new StringReader(str));
            // 保存相应词汇
            CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
            // OffsetAttribute ota = stream.addAttribute(OffsetAttribute.class);
            stream.reset();
            while (stream.incrementToken()) {
                System.out.print("[" + cta + "]");
                // System.out.print("[" + ota.endOffset() + "," +
                // ota.startOffset() + "]");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * ICTCLASAnalyzer analyzer = new ICTCLASAnalyzer(); InputStreamReader
         * read = new InputStreamReader(new FileInputStream(new File("test")),
         * "gbk"); BufferedReader reader = new BufferedReader(read);
         * TokenStreamComponents tsc = analyzer.createComponents("contents",
         * reader); Tokenizer t = tsc.getTokenizer(); while (t.incrementToken())
         * { System.out.println(t.getAttribute(CharTermAttribute.class) +
         * "----->" + t.getAttribute(TypeAttribute.class).type() + " "); }
         * analyzer.close();
         */

        Analyzer aly = new ICTCLASAnalyzer();

        String str = "点击下载超女纪敏佳深受观众喜爱。禽流感爆发在非典之后。";

        test.displayToken(str, aly);
        System.out.println("-----------------------------");
        NLPIR testNLPIR30 = new NLPIR();
        // init
        String argu = "";
        if (NLPIR.NLPIR_Init(argu.getBytes("GB2312"), 0) == false) {
            System.out.println("Init Fail!");
            return;
        }
        System.out.println("Init Success!");
        // analysis
        str = "点击下载超女纪敏佳深受观众喜爱。禽流感爆发在非典之后。";
        byte[] nativeBytes = testNLPIR30.NLPIR_ParagraphProcess(str.getBytes("GB2312"), 1);
        String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
        System.out.println(nativeStr);

        // .NLPIR_Exit();

    }

}