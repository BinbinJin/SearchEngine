package org.jbb.se;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import kevin.zhang.NLPIR;

public class ICTCLASTokenizer extends Tokenizer {
    private List<String> tokens;
    private Iterator<String> tokenIter;
    private CharTermAttribute termAtt;
    private TypeAttribute typeAtt;
    private NLPIR nlpir;

    public ICTCLASTokenizer(Reader reader) {
        super(reader);
        this.nlpir = new NLPIR();
        this.termAtt = this.addAttribute(CharTermAttribute.class);
        this.typeAtt = this.addAttribute(TypeAttribute.class);
    }

    protected List<String> tokenizerReader() {
        List<String> result = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(this.input);
        String s, content = "";
        try {
            while ((s = bf.readLine()) != null) {
                content = content + s + "\n";
            }
            byte[] contentByte = this.nlpir.NLPIR_ParagraphProcess(content.getBytes("GB2312"), 1);
            String contentStr = new String(contentByte, "GB2312");
            String[] terms = contentStr.split("\\s+");
            for (String string : terms) {
                result.add(string);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        /*
         * ´Ë¶Î´úÂë¿ÉÒÔÓÃÀ´¹ýÂË´Ê int pos; String term,type; for (String string : terms){
         * pos=string.lastIndexOf('/'); if (pos==-1)continue;
         * term=string.substring(0, pos); type=string.substring(pos+1,
         * string.length()); if (accept(term,type)){ //ï¿½Ð¶ï¿½ï¿½Ç·ï¿½ï¿½ï¿½ï¿½ï¿½Òªï¿½Ä´ï¿½ï¿½ï¿½
         * result.add(string); } }
         */
        return result;
    }

    /*
     * private boolean accept(String term, String type) { if
     * (type.startsWith("n") // || type.startsWith("t") // ||
     * type.startsWith("s") // || type.startsWith("f") // ||
     * type.startsWith("a") // || type.startsWith("v") // ||
     * type.startsWith("b") // || type.startsWith("z") // ||
     * type.startsWith("r") // || type.startsWith("m") // ||
     * type.startsWith("q") // || type.startsWith("d") // ||
     * type.startsWith("p") // || type.startsWith("c") // ||
     * type.startsWith("u") // || type.startsWith("e") // ||
     * type.startsWith("y") // || type.startsWith("o") // ||
     * type.startsWith("h") // || type.startsWith("k") // ||
     * type.startsWith("x") // URL || type.startsWith("w") // ) { return true; }
     * return false; }
     */

    @Override
    public boolean incrementToken() {// µÃµ½ÏÂÒ»´Î´Ê
        this.clearAttributes();
        if (this.tokenIter.hasNext()) {
            String tokenstr = this.tokenIter.next();
            int pos = tokenstr.lastIndexOf('/');
            if (pos != -1) {
                this.termAtt.append(tokenstr.substring(0, pos));
                this.termAtt.setLength(pos);
                this.typeAtt.setType(tokenstr.substring(pos, tokenstr.length()));
                return true;
            }
        }
        return false;
    }

    @Override
    public void reset() {
        this.tokens = this.tokenizerReader();
        this.tokenIter = this.tokens.iterator();
    }

}
