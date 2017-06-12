package ${package}.core.model;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ${package}.core.util.StringHelper;

/**
 * TODO Request 검색 관련 VO class
 *
 * @author mike Ryu, BD Apis
 * @date 2015. 3. 09
 * @version 1.0
 */
public class SearchRequest {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    String q;
    Integer page;
    Integer size;
    Integer offset;
    Integer limit;
    HashMap<String, Object> query;
    PageInfo pageInfo;
    String[] checkBoxValue;

    /*
     * Request로 받은 데이타에 데이타에 대해서 Mybatis에서 사용할 데이타로 변경한다.
     * 
     * @param
     */
    @SuppressWarnings("unchecked")
    public void setData() {
        ObjectMapper mapper = new ObjectMapper();
        try {

            if (query != null && query.size() > 0)
                ; // do nothing
            else if (!StringHelper.isEmpty(this.q))
                query = (HashMap<String, Object>) mapper.readValue(q, new TypeReference<Map<String, Object>>() {
                });
            else
                query = new HashMap<String, Object>();

            if (page != null && size != null)
                this.pageInfo = new PageInfo(this.page, this.size);

        } catch (Exception e) {
            e.printStackTrace();
            query = new HashMap<String, Object>();
        }
    }

    public HashMap<String, Object> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, Object> query) {
        this.query = query;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void addQueryData(String key, Object value) {
        setData();
        query.put(key, value);
    }

    public Object getQueryData(String key) {
        setData();
        return query.get(key);
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

	public String[] getCheckBoxValue() {
		return checkBoxValue;
	}

	public void setCheckBoxValue(String[] checkBoxValue) {
		this.checkBoxValue = checkBoxValue;
	}
    
}
