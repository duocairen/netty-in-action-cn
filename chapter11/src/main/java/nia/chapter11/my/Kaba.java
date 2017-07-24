package nia.chapter11.my;

import java.util.Map;

/**
 * Kaba protocol entity
 *
 * @author hank
 * @create 2017-07-18 15:28
 **/
public class Kaba {

    private int code;
    private String tid;
    private int version;
    private String url;
    private Map<String, String> headers;
    private String body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Kaba{" +
                "code=" + code +
                ", tid='" + tid + '\'' +
                ", version=" + version +
                ", url='" + url + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
