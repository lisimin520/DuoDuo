package cn.edu.scujcc.duoduo;

/**
 * 服务器返回的消息。
 */
public class Result<T> {
    public final static int OK = 1;
    public final static int ERROR = 0;

    private int status;
    private String message;
    private T data;

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}