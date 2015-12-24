package orz;

import kevin.zhang.NLPIR;

public class TestNLPIR {
    public TestNLPIR() {
        try {
            String sInput = "�Ż�ƽ�Ƴ���NLPIR�ִ�ϵͳ������ICTCLAS2013�������´�ʶ�𡢹ؼ�����ȡ��΢���ִʹ��ܡ�";

            // ����Ӧ�ִ�
            test(sInput);

        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            String sInput = "�Ż�ƽ�Ƴ���NLPIR�ִ�ϵͳ������ICTCLAS2013�������´�ʶ�𡢹ؼ�����ȡ��΢���ִʹ��ܡ�";

            // ����Ӧ�ִ�
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

            // �����û��ʵ�ǰ
            byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(sInput.getBytes("GB2312"), 1);
            String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");

            System.out.println("�ִʽ��Ϊ�� " + nativeStr);

        } catch (Exception ex) {
        }
    }
}
