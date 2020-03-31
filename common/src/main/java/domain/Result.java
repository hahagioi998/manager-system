package domain;

public class Result {
    private boolean flag;
    private Integer code;
    private String msg;
    private Object data;
    private Integer count;

    public Result(boolean flag, Integer code, String msg, Integer count) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.count = count;
    }
    public Result(boolean flag, Integer code, String msg, Object data, Integer count) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public Result(boolean flag, Integer code, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getCount() {
        return count;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
