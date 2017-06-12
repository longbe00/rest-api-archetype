package ${package}.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @author lee
 * @date 2016-05-19
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCommon {
    /**
     * The Status.
     */
    String status;
    /**
     * The Message.
     */
    String message;
}
