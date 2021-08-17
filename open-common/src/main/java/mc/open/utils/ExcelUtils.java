package mc.open.utils;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.*;

/**
 * @author macheng
 * @date 2021/8/17 9:00
 */
public class ExcelUtils {

    private  ExcelUtils(){

    }
    public static <T> ExcelWriter getExcelOnlyAlias(Class<T> clz, String sheetName, List<T> dataList) {
        ExcelWriter writer = null;
        if (writer == null) {
            writer = ExcelUtil.getWriterWithSheet(sheetName);
        } else {
            writer.setSheet(sheetName);
        }
        Map<String, String> aliasMap = getMap(clz);
        writer.clearHeaderAlias();
        for (String key : aliasMap.keySet()) {
            writer.addHeaderAlias(key, aliasMap.get(key));
        }
        writer.setOnlyAlias(true);
        writer.autoSizeColumnAll();
        writer.write(dataList, true);
        return writer;
    }

    public static <T> Map<String, String> getMap(Class<T> clz) {
        Map<String, String> targetMap = new LinkedHashMap<>();
        Collection<BeanDesc.PropDesc> props = BeanUtil.getBeanDesc(clz).getProps();
        Iterator<BeanDesc.PropDesc> var8 = props.iterator();
        String key;
        String value;
        while (var8.hasNext()) {
            BeanDesc.PropDesc prop = var8.next();
            key = prop.getFieldName();
            value = prop.getField().getName();
            if (null != key && !value.equals(key)) {
                targetMap.put(key, key);
            }
        }
        return targetMap;
    }
}
