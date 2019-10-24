/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.code.entity;

import com.google.common.collect.Lists;
import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

import java.util.List;

/**
 * 代码Entity
 * @author wzy
 * @version 2018-08-13
 */
public class TBzCode extends DataEntity<TBzCode> {
	
	private static final long serialVersionUID = 1L;
	/*private String column1;		// column1
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private String column6;		// column6
	private String column7;		// column7
	private String column8;		// column8
	private String column9;		// column9
	private String column10;		// column10
	private String column11;		// column11
	private String column12;		// column12
	private String column13;		// column13
	private String column14;		// column14
	private String column15;		// column15
	private String column16;		// column16
	private String column17;		// column17
	private String column18;		// column18
	private String column19;		// column19
	private String column20;		// column20
	private String column21;		// column21
	private String column22;		// column22
	private String column23;		// column23
	private String column24;		// column24
	private String column25;		// column25
	private String column26;		// column26
	private String column27;		// column27
	private String column28;		// column28
	private String column29;		// column29
	private String column30;		// column30*/

	private String sql;

	private List<String> selectedFields = Lists.newArrayList();

	public List<String> getSelectedFields() {
		return selectedFields;
	}

	public void setSelectedFields(List<String> selectedFields) {
		this.selectedFields = selectedFields;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public TBzCode() {
		super();
	}

	public TBzCode(String id){
		super(id);
	}

	/*@Length(min=0, max=255, message="column1长度必须介于 0 和 255 之间")
	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	
	@Length(min=0, max=255, message="column2长度必须介于 0 和 255 之间")
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	
	@Length(min=0, max=255, message="column3长度必须介于 0 和 255 之间")
	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	@Length(min=0, max=255, message="column4长度必须介于 0 和 255 之间")
	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	@Length(min=0, max=255, message="column5长度必须介于 0 和 255 之间")
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	
	@Length(min=0, max=255, message="column6长度必须介于 0 和 255 之间")
	public String getColumn6() {
		return column6;
	}

	public void setColumn6(String column6) {
		this.column6 = column6;
	}
	
	@Length(min=0, max=255, message="column7长度必须介于 0 和 255 之间")
	public String getColumn7() {
		return column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}
	
	@Length(min=0, max=255, message="column8长度必须介于 0 和 255 之间")
	public String getColumn8() {
		return column8;
	}

	public void setColumn8(String column8) {
		this.column8 = column8;
	}
	
	@Length(min=0, max=255, message="column9长度必须介于 0 和 255 之间")
	public String getColumn9() {
		return column9;
	}

	public void setColumn9(String column9) {
		this.column9 = column9;
	}
	
	@Length(min=0, max=255, message="column10长度必须介于 0 和 255 之间")
	public String getColumn10() {
		return column10;
	}

	public void setColumn10(String column10) {
		this.column10 = column10;
	}
	
	@Length(min=0, max=255, message="column11长度必须介于 0 和 255 之间")
	public String getColumn11() {
		return column11;
	}

	public void setColumn11(String column11) {
		this.column11 = column11;
	}
	
	@Length(min=0, max=255, message="column12长度必须介于 0 和 255 之间")
	public String getColumn12() {
		return column12;
	}

	public void setColumn12(String column12) {
		this.column12 = column12;
	}
	
	@Length(min=0, max=255, message="column13长度必须介于 0 和 255 之间")
	public String getColumn13() {
		return column13;
	}

	public void setColumn13(String column13) {
		this.column13 = column13;
	}
	
	@Length(min=0, max=255, message="column14长度必须介于 0 和 255 之间")
	public String getColumn14() {
		return column14;
	}

	public void setColumn14(String column14) {
		this.column14 = column14;
	}
	
	@Length(min=0, max=255, message="column15长度必须介于 0 和 255 之间")
	public String getColumn15() {
		return column15;
	}

	public void setColumn15(String column15) {
		this.column15 = column15;
	}
	
	@Length(min=0, max=255, message="column16长度必须介于 0 和 255 之间")
	public String getColumn16() {
		return column16;
	}

	public void setColumn16(String column16) {
		this.column16 = column16;
	}
	
	@Length(min=0, max=255, message="column17长度必须介于 0 和 255 之间")
	public String getColumn17() {
		return column17;
	}

	public void setColumn17(String column17) {
		this.column17 = column17;
	}
	
	@Length(min=0, max=255, message="column18长度必须介于 0 和 255 之间")
	public String getColumn18() {
		return column18;
	}

	public void setColumn18(String column18) {
		this.column18 = column18;
	}
	
	@Length(min=0, max=255, message="column19长度必须介于 0 和 255 之间")
	public String getColumn19() {
		return column19;
	}

	public void setColumn19(String column19) {
		this.column19 = column19;
	}
	
	@Length(min=0, max=255, message="column20长度必须介于 0 和 255 之间")
	public String getColumn20() {
		return column20;
	}

	public void setColumn20(String column20) {
		this.column20 = column20;
	}
	
	@Length(min=0, max=255, message="column21长度必须介于 0 和 255 之间")
	public String getColumn21() {
		return column21;
	}

	public void setColumn21(String column21) {
		this.column21 = column21;
	}
	
	@Length(min=0, max=255, message="column22长度必须介于 0 和 255 之间")
	public String getColumn22() {
		return column22;
	}

	public void setColumn22(String column22) {
		this.column22 = column22;
	}
	
	@Length(min=0, max=255, message="column23长度必须介于 0 和 255 之间")
	public String getColumn23() {
		return column23;
	}

	public void setColumn23(String column23) {
		this.column23 = column23;
	}
	
	@Length(min=0, max=255, message="column24长度必须介于 0 和 255 之间")
	public String getColumn24() {
		return column24;
	}

	public void setColumn24(String column24) {
		this.column24 = column24;
	}
	
	@Length(min=0, max=255, message="column25长度必须介于 0 和 255 之间")
	public String getColumn25() {
		return column25;
	}

	public void setColumn25(String column25) {
		this.column25 = column25;
	}
	
	@Length(min=0, max=255, message="column26长度必须介于 0 和 255 之间")
	public String getColumn26() {
		return column26;
	}

	public void setColumn26(String column26) {
		this.column26 = column26;
	}
	
	@Length(min=0, max=255, message="column27长度必须介于 0 和 255 之间")
	public String getColumn27() {
		return column27;
	}

	public void setColumn27(String column27) {
		this.column27 = column27;
	}
	
	@Length(min=0, max=255, message="column28长度必须介于 0 和 255 之间")
	public String getColumn28() {
		return column28;
	}

	public void setColumn28(String column28) {
		this.column28 = column28;
	}
	
	@Length(min=0, max=255, message="column29长度必须介于 0 和 255 之间")
	public String getColumn29() {
		return column29;
	}

	public void setColumn29(String column29) {
		this.column29 = column29;
	}
	
	@Length(min=0, max=255, message="column30长度必须介于 0 和 255 之间")
	public String getColumn30() {
		return column30;
	}

	public void setColumn30(String column30) {
		this.column30 = column30;
	}*/
	
}