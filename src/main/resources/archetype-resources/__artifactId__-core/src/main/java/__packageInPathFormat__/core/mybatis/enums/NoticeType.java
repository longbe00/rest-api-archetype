/**
 * 
 */
package ${package}.core.mybatis.enums;

import ${package}.core.annotation.DbValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mike
 *
 */
@AllArgsConstructor
public enum NoticeType {

	TEST("TEST"), TEST123("TEST123");

	private @Getter(onMethod = @__(@DbValue) ) String code;

}
