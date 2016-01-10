package com.jzd.record.db;

public class CompanyClass {

	private int _id, boot_on_weekend;
	private String company_name, company_city, company_address, device_location,company_aera, main_contact, net_contact, boot_time,
			shut_time, company_type, lan_type, hddsn, qrcode, wifi_ssid, wifi_password, note1, note2, factory,
			chat_date, install_date, create_date, modify_date, net_phone, main_phone;

	public String getDevice_location() {
		return device_location;
	}

	public void setDevice_location(String device_location) {
		this.device_location = device_location;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}

	public String getChat_date() {
		return chat_date;
	}

	public void setChat_date(String chat_date) {
		this.chat_date = chat_date;
	}

	public String getInstall_date() {
		return install_date;
	}

	public void setInstall_date(String install_date) {
		this.install_date = install_date;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getmain_phone() {
		return main_phone;
	}

	public void setmain_phone(String main_phone) {
		this.main_phone = main_phone;
	}

	public String getnet_phone() {
		return net_phone;
	}

	public void setnet_phone(String net_phone) {
		this.net_phone = net_phone;
	}

	public int getBoot_on_weekend() {
		return boot_on_weekend;
	}

	public void setBoot_on_weekend(int boot_on_weekend) {
		this.boot_on_weekend = boot_on_weekend;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_city() {
		return company_city;
	}

	public void setCompany_city(String company_city) {
		this.company_city = company_city;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public String getCompany_aera() {
		return company_aera;
	}

	public void setCompany_aera(String company_aera) {
		this.company_aera = company_aera;
	}

	public String getmain_contact() {
		return main_contact;
	}

	public void setmain_contact(String main_contact) {
		this.main_contact = main_contact;
	}

	public String getnet_contact() {
		return net_contact;
	}

	public void setnet_contact(String net_contact) {
		this.net_contact = net_contact;
	}

	public String getBoot_time() {
		return boot_time;
	}

	public void setBoot_time(String boot_time) {
		this.boot_time = boot_time;
	}

	public String getShut_time() {
		return shut_time;
	}

	public void setShut_time(String shut_time) {
		this.shut_time = shut_time;
	}

	public String getCompany_type() {
		return company_type;
	}

	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	public String getLan_type() {
		return lan_type;
	}

	public void setLan_type(String lan_type) {
		this.lan_type = lan_type;
	}

	public String getHddsn() {
		return hddsn;
	}

	public void setHddsn(String hddsn) {
		this.hddsn = hddsn;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getWifi_ssid() {
		return wifi_ssid;
	}

	public void setWifi_ssid(String wifi_ssid) {
		this.wifi_ssid = wifi_ssid;
	}

	public String getWifi_password() {
		return wifi_password;
	}

	public void setWifi_password(String wifi_password) {
		this.wifi_password = wifi_password;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	@Override
	public String toString() {
		return "CompanyClass [_id=" + _id + ", main_phone=" + main_phone + ", net_phone=" + net_phone
				+ ", boot_on_weekend=" + boot_on_weekend + ", company_name=" + company_name + ", company_city="
				+ company_city + ", company_address=" + company_address + ", company_aera=" + company_aera
				+ ", main_contact=" + main_contact + ", net_contact=" + net_contact + ", boot_time=" + boot_time
				+ ", shut_time=" + shut_time + ", company_type=" + company_type + ", lan_type=" + lan_type
				+ ", hddsn=" + hddsn + ", qrcode=" + qrcode + ", wifi_ssid=" + wifi_ssid
				+ ", wifi_password=" + wifi_password + ", note1=" + note1 + ", note2=" + note2 + ", factory=" + factory
				+ ", chat_date=" + chat_date + ", install_date=" + install_date + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + boot_on_weekend;
		result = prime * result + ((boot_time == null) ? 0 : boot_time.hashCode());
		result = prime * result + ((chat_date == null) ? 0 : chat_date.hashCode());
		result = prime * result + ((company_address == null) ? 0 : company_address.hashCode());
		result = prime * result + ((company_aera == null) ? 0 : company_aera.hashCode());
		result = prime * result + ((company_city == null) ? 0 : company_city.hashCode());
		result = prime * result + ((company_name == null) ? 0 : company_name.hashCode());
		result = prime * result + ((company_type == null) ? 0 : company_type.hashCode());
		result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
		result = prime * result + ((factory == null) ? 0 : factory.hashCode());
		result = prime * result + ((main_contact == null) ? 0 : main_contact.hashCode());
		result = prime * result + ((main_phone == null) ? 0 : main_phone.hashCode());
		result = prime * result + ((hddsn == null) ? 0 : hddsn.hashCode());
		result = prime * result + ((install_date == null) ? 0 : install_date.hashCode());
		result = prime * result + ((lan_type == null) ? 0 : lan_type.hashCode());
		result = prime * result + ((modify_date == null) ? 0 : modify_date.hashCode());
		result = prime * result + ((note1 == null) ? 0 : note1.hashCode());
		result = prime * result + ((note2 == null) ? 0 : note2.hashCode());
		result = prime * result + ((qrcode == null) ? 0 : qrcode.hashCode());
		result = prime * result + ((net_contact == null) ? 0 : net_contact.hashCode());
		result = prime * result + ((net_phone == null) ? 0 : net_phone.hashCode());
		result = prime * result + ((shut_time == null) ? 0 : shut_time.hashCode());
		result = prime * result + ((wifi_password == null) ? 0 : wifi_password.hashCode());
		result = prime * result + ((wifi_ssid == null) ? 0 : wifi_ssid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyClass other = (CompanyClass) obj;
		if (_id != other._id)
			return false;
		if (boot_on_weekend != other.boot_on_weekend)
			return false;
		if (boot_time == null) {
			if (other.boot_time != null)
				return false;
		} else if (!boot_time.equals(other.boot_time))
			return false;
		if (chat_date == null) {
			if (other.chat_date != null)
				return false;
		} else if (!chat_date.equals(other.chat_date))
			return false;
		if (company_address == null) {
			if (other.company_address != null)
				return false;
		} else if (!company_address.equals(other.company_address))
			return false;
		if (company_aera == null) {
			if (other.company_aera != null)
				return false;
		} else if (!company_aera.equals(other.company_aera))
			return false;
		if (company_city == null) {
			if (other.company_city != null)
				return false;
		} else if (!company_city.equals(other.company_city))
			return false;
		if (company_name == null) {
			if (other.company_name != null)
				return false;
		} else if (!company_name.equals(other.company_name))
			return false;
		if (company_type == null) {
			if (other.company_type != null)
				return false;
		} else if (!company_type.equals(other.company_type))
			return false;
		if (create_date == null) {
			if (other.create_date != null)
				return false;
		} else if (!create_date.equals(other.create_date))
			return false;
		if (factory == null) {
			if (other.factory != null)
				return false;
		} else if (!factory.equals(other.factory))
			return false;
		if (main_contact == null) {
			if (other.main_contact != null)
				return false;
		} else if (!main_contact.equals(other.main_contact))
			return false;
		if (main_phone == null) {
			if (other.main_phone != null)
				return false;
		} else if (!main_phone.equals(other.main_phone))
			return false;
		if (hddsn == null) {
			if (other.hddsn != null)
				return false;
		} else if (!hddsn.equals(other.hddsn))
			return false;
		if (install_date == null) {
			if (other.install_date != null)
				return false;
		} else if (!install_date.equals(other.install_date))
			return false;
		if (lan_type == null) {
			if (other.lan_type != null)
				return false;
		} else if (!lan_type.equals(other.lan_type))
			return false;
		if (modify_date == null) {
			if (other.modify_date != null)
				return false;
		} else if (!modify_date.equals(other.modify_date))
			return false;
		if (note1 == null) {
			if (other.note1 != null)
				return false;
		} else if (!note1.equals(other.note1))
			return false;
		if (note2 == null) {
			if (other.note2 != null)
				return false;
		} else if (!note2.equals(other.note2))
			return false;
		if (qrcode == null) {
			if (other.qrcode != null)
				return false;
		} else if (!qrcode.equals(other.qrcode))
			return false;
		if (net_contact == null) {
			if (other.net_contact != null)
				return false;
		} else if (!net_contact.equals(other.net_contact))
			return false;
		if (net_phone == null) {
			if (other.net_phone != null)
				return false;
		} else if (!net_phone.equals(other.net_phone))
			return false;
		if (shut_time == null) {
			if (other.shut_time != null)
				return false;
		} else if (!shut_time.equals(other.shut_time))
			return false;
		if (wifi_password == null) {
			if (other.wifi_password != null)
				return false;
		} else if (!wifi_password.equals(other.wifi_password))
			return false;
		if (wifi_ssid == null) {
			if (other.wifi_ssid != null)
				return false;
		} else if (!wifi_ssid.equals(other.wifi_ssid))
			return false;
		return true;
	}

}
