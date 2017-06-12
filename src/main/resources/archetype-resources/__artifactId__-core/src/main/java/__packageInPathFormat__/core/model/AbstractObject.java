/*
 * Copyright (c) 2013 SK Telecom.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK Telecom.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK Telecom.
 */
package ${package}.core.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Kolonglobal UPPP의 모든 도메인 객체가 상속 받아야 할 기본 클래스. <br/>
 *
 * @author Mike Ryu
 */

@Data
public abstract class AbstractObject {

    private String createId;

    private Date createDateTime;

    private String modifyId;

    private Date modifyDateTime;

    // 사용여부
    private Boolean useYn;

    // 삭제여부
    @JsonIgnore
    private Boolean deleteYn;

}
