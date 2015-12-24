package org.jbb.se;

import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

import kevin.zhang.NLPIR;

public class ICTCLASAnalyzer extends Analyzer {
    private NLPIR nlpir;

    public ICTCLASAnalyzer() throws UnsupportedEncodingException {
        this.nlpir = new NLPIR();
        String initPath = "C:\\Users\\JinBinbin\\workspace\\SearchEngine";
        // ��ʼ��
        if (NLPIR.NLPIR_Init(initPath.getBytes("GB2312"), 0) == false) {
            System.out.println("Init Fail!");
            return;
        }
        this.nlpir.NLPIR_SetPOSmap(0);
        /*
         * �˶δ�����������û��ʵ��� int count=0; String usrdir="usrdict.txt"; byte[]
         * usrdir_b=usrdir.getBytes();
         * count=nlpir.NLPIR_ImportUserDict(usrdir_b);
         * System.out.println("�û��ʵ���:"+count);
         */

    }

    @Override
    public TokenStreamComponents createComponents(String fieldname, Reader reader) {
        Tokenizer ictclastokenizer = new ICTCLASTokenizer(reader);
        TokenStreamComponents tokenstreamcomponents = new TokenStreamComponents(ictclastokenizer);
        return tokenstreamcomponents;
    }

}
