package com.example.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    /**
     * ����GET����
     *
     * @param url
     *            Ŀ�ĵ�ַ
     * @param parameters
     *            �������Map���͡�
     * @return Զ����Ӧ���
     */
    public static String sendGet(String url, Map<String, String> parameters) {
        String result = "";// ���صĽ��
        BufferedReader in = null;// ��ȡ��Ӧ������
        StringBuffer sb = new StringBuffer();// �洢����
        String params = "";// ����֮��Ĳ���
        try {
            // �����������
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"))
                            .append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;
            System.out.println(full_url);
            // ����URL����
            java.net.URL connURL = new java.net.URL(full_url);
            // ��URL����
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
            // ����ͨ������
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // ����ʵ�ʵ�����
            httpConn.connect();
            // ��Ӧͷ����ȡ
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : headers.keySet()) {
                System.out.println(key + "\t��\t" + headers.get(key));
            }
            // ����BufferedReader����������ȡURL����Ӧ,�����ñ��뷽ʽ
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // ��ȡ���ص�����
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * ����POST����
     *
     * @param url
     *            Ŀ�ĵ�ַ
     * @param parameters
     *            �������Map���͡�
     * @return Զ����Ӧ���
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        String result = "";// ���صĽ��
        BufferedReader in = null;// ��ȡ��Ӧ������
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// �����������
        String params = "";// ����֮��Ĳ���
        try {
            // �����������
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"))
                            .append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // ����URL����
            java.net.URL connURL = new java.net.URL(url);
            // ��URL����
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
            // ����ͨ������
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // ����POST��ʽ
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // ��ȡHttpURLConnection�����Ӧ�������
            out = new PrintWriter(httpConn.getOutputStream());
            // �����������
            out.write(params);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ�����ñ��뷽ʽ
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // ��ȡ���ص�����
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * �������������
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("name", "jj");
        String result = sendGet("http://localhost:8080/SearchEngine/HelloServlet", parameters);
        System.out.println(result);
    }
}