package com.swpu.apply_server.vo;

import com.swpu.apply_server.domain.Apply;

public class ApplyVo extends Apply {
    private Integer page=1;
    private Integer limit=10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
