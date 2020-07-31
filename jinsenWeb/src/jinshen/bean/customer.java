package jinshen.bean;
//生产业主信息
public class customer {
	private int cutstomerid;
	private String kname;
	private String contractnum;
	private String address;
	private String company;
	private String IDCard;//身份证号
	private String telephone;//联系电话
	private String shipaddress;//发货地址
	public int getCutstomerid() {
		return cutstomerid;
	}
	public void setCutstomerid(int cutstomerid) {
		this.cutstomerid = cutstomerid;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public String getContractnum() {
		return contractnum;
	}
	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getShipaddress() {
		return shipaddress;
	}
	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}
	

}
