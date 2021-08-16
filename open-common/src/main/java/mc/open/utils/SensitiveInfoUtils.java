package mc.open.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author macheng
 * @date 2020/12/16 10:03
 */
public class SensitiveInfoUtils {

    private SensitiveInfoUtils() {

    }

    /**
     * [身份证号] 前3位、后3位明文展示，其他部分用*代替
     *
     * @return
     */
    public static String idNo(String id, String mask) {
        if (StringUtils.isBlank(id)) {
            return "";
        }
        int length = StringUtils.length(id);
        return StringUtils.rightPad(StringUtils.left(id, 3), length - 3, mask).concat(StringUtils.right(id, 3));
    }

    /**
     * [手机号码] 前3位、后4位明文展示，其他部分用*代替
     *
     * @param num
     * @return
     */
    public static String mobilePhone(String num, String mask) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        int length = StringUtils.length(num);
        return StringUtils.rightPad(StringUtils.left(num, 3), length - 4, mask).concat(StringUtils.right(num, 4));
    }

    /**
     * [电子邮箱] @之前的部分，前2位和最后1位明文展示，其他部分用固定的8个*代替；@及以后的部分明文展示
     *
     * @param email
     * @return
     */
    public static String email(String email, String mask) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            return StringUtils.rightPad(StringUtils.left(email, 2), 10, mask).concat(StringUtils.mid(email, index - 1, StringUtils.length(email)));
        }

    }

    /**
     * [地址] 前6个汉字和最后2个汉字明文展示，其他部分用8个*代替
     *
     * @param address
     * @return
     */
    public static String address(String address, String mask) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        int length = StringUtils.length(address);

        if (length <= 4) {
            return StringUtils.rightPad(StringUtils.left(address, 1), 10, mask);
        }

        if (length <= 10) {
            return StringUtils.rightPad(StringUtils.left(address, 2), 10, mask).concat(StringUtils.right(address, 2));
        }

        return StringUtils.rightPad(StringUtils.left(address, 6), 14, mask).concat(StringUtils.right(address, 2));
    }

}
