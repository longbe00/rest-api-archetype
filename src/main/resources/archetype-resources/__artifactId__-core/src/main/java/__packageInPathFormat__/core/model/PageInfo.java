package ${package}.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Page처리를 위한 클래스
 *
 * @author mike Ryu
 */
@Data
public class PageInfo {
    private Integer page;
    private Integer size;
    private Integer resultCount;
    private Integer totalCount;

    @JsonIgnore
    private Integer startRowNum;
    @JsonIgnore
    private Integer endRowNum;

    public PageInfo() {
        super();
    }

    public PageInfo(Integer page, Integer size) {
        super();
        this.page = page;
        this.size = size;
    }

    public PageInfo(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PageInfo(int page, int size, int resultCount, int totalCount) {
        this.page = page;
        this.size = size;
        this.resultCount = resultCount;
        this.totalCount = totalCount;
    }

    public Integer getStartRowNum() {
        return (this.page - 1) * this.size;
    }

}
