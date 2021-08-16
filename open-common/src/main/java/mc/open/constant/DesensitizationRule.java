package mc.open.constant;


/**
 * @author macheng
 * @date 2020/12/16 9:57
 */
public enum DesensitizationRule {
    /**
     * rule
     */
    ID("I"), EMAIL("E"), ADRRESS("A"), MOBILE("M");

    private String code;


    DesensitizationRule(String code) {
        this.code = code;
    }

    public static DesensitizationRule fromCode(String code) {
        DesensitizationRule[] stream = DesensitizationRule.values();
        for (DesensitizationRule des : stream) {
            if (des.code.equals(code)) {
                return des;
            }
        }
        throw new RuntimeException("未知脱敏规则");
    }

    public String code() {
        return code;
    }
}
