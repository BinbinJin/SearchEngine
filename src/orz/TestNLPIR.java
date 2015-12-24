package orz;

import kevin.zhang.NLPIR;

public class TestNLPIR {
    public TestNLPIR() {
        try {
            String sInput = "张华平推出的NLPIR分词系统，又名ICTCLAS2013，新增新词识别、关键词提取、微博分词功能。";

            // 自适应分词
            test(sInput);

        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            String sInput = "张华平推出的NLPIR分词系统，又名ICTCLAS2013，新增新词识别、关键词提取、微博分词功能。";

            // 自适应分词
            test(sInput);
            System.out.println(System.getProperty("java.library.path"));

        } catch (Exception ex) {
        }

    }

    public static void test(String sInput) {
        try {
            NLPIR testNLPIR = new NLPIR();

            String argu = ".";
            System.out.println("NLPIR_Init");
            if (NLPIR.NLPIR_Init(argu.getBytes("GB2312"), 0) == false) {
                System.out.println("Init Fail!");
                return;
            }

            // 导入用户词典前
            byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(sInput.getBytes("GB2312"), 1);
            String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");

            System.out.println("分词结果为： " + nativeStr);

        } catch (Exception ex) {
        }
    }
}
