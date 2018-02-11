package cn.zhx.common.conversion;

import org.springframework.core.convert.converter.Converter;

/**
 * 去掉前后空格
 * <S  从页面传递过来的参数的类型   
 *  T> 转换后的类型
 * @author lx
 *
 */
public class CustomConversion implements Converter<String, String>{

	@Override
	public String convert(String source) {
		// TODO Auto-generated method stub
		try {// "  "
			if(null != source){
				source = source.trim();
				if(!"".equals(source)){
					return source;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
