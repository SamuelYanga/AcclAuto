package com.objectivasoftware.accl.core.util.enu;

public enum ProvinceInfo {

	beijing("北京市", "北京市", "海淀区", "天安门"),
	jiangsu("江苏省", "扬州市", "维扬区", "天安门"),
	heilongjiang("黑龙江省", "大兴安岭地区", "漠河县", "天安门"),
	henan("河南省", "周口市", "扶沟县", "天安门"),
	hainan("海南省", "三亚市", "海棠区", "天安门"),
	xinjiang("新疆自治区", "克拉玛依市", "乌尔禾区", "天安门"),
	neimeng("内蒙古自治区", "通辽市", "科尔沁区", "天安门"),
	hebei("河北省", "衡水市", "武邑县", "天安门"),
	shanghai("上海市", "上海市", "浦东新区", "天安门"),
	liaoning("辽宁省", "大连市", "甘井子区", "天安门"),
	jilin("吉林省", "长春市", "二道区", "天安门"),
	anhui("安徽省", "宿州市", "砀山县", "天安门"),
	jiangxi("江西省", "萍乡市", "芦溪县", "天安门"),
	chongqing("重庆市", "重庆市", "渝北区", "天安门"),
	hunan("湖南省", "长沙市", "芙蓉区", "天安门"),
	guangdong("广东省", "中山市", "五桂山", "天安门"),
	guizhou("贵州省", "遵义市", "正安县", "天安门"),
	sichuang("四川省", "成都市", "武侯区", "天安门"),
	shangxi("陕西省", "汉中市", "佛坪县", "天安门"),
	ningxia("宁夏自治区", "银川市", "西夏区", "天安门"),
	qinghai("青海省", "西宁市", "湟源县", "天安门"),
	hainanceshi("海南测试省", "海南测试省", "海南测试省", "天安门"),
	shanxi("山西省", "吕梁市", "文水县", "天安门"),
	zhejiang("浙江省", "杭州市", "余杭区", "天安门"),
	shandong("山东省", "济南市", "长清区", "天安门"),
	tianjin("天津市", "天津市", "南开区", "天安门"),
	fujian("福建省", "莆田市", "荔城区", "天安门"),
	huibei("湖北省", "荆门市", "沙洋县", "天安门"),
	guangxi("广西自治区", "北海市", "银海区", "天安门"),
	yunnan("云南省", "丽江市", "古城区", "天安门"),
	gansu("甘肃省", "天水市", "秦州区", "天安门"),
	xizang("西藏自治区", "拉萨市", "达孜县", "天安门");

	private String province;
	private String defaultCity;
	private String defaultZone;
	private String defaultAddress;

	ProvinceInfo(String province, String defaultCity, String defaultZone, String defaultAddress) {
		this.province = province;
		this.defaultCity = defaultCity;
		this.defaultZone = defaultZone;
		this.defaultAddress = defaultAddress;
	}

	public String getProvince() {
		return province;
	}

	public String getDefaultCity() {
		return defaultCity;
	}

	public String getDefaultZone() {
		return defaultZone;
	}

	public String getDefaultAddress() {
		return defaultAddress;
	}

	public static ProvinceInfo getProvinceByName(String provinceName) {
		for (ProvinceInfo province : ProvinceInfo.values()) {
			if (provinceName.equals(province.getProvince())) {
				return province;
			}
		}
		return null;
	}

}
