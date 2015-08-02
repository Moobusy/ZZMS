package tools.packet.provider;

/**
 *
 * @author 黯淡
 */
public enum SpecialEffectType {
    //等級提升[182-完成]
//等級提升[182-完成]
//等級提升[182-完成]
//等級提升[182-完成]
//等級提升[182-完成]
//等級提升[182-完成]
//等級提升[182-完成]
//等級提升[182-完成]
    LEVEL_UP(0x0),
    //近端技能特效[182-完成]
    LOCAL_SKILL_EFFECT(0x1),
    //遠端技能特效[182-完成]
    REMOTE_SKILL_EFFECT(0x2),
    //神之子特效[178-完成]
    ZERO_EFFECT(0x3),
    //機甲戰神-輔助機器特效[178-完成]
    MECHANIC_EFFECT(0x4),
    UNK_5(0x5),
    UNK_6(0x6),
    //物品獲得/丟棄文字特效[182-完成]
    ITEM_OPERATION_EFFECT(0x7),
    UNK_8(0x8),
    //技能飛行體特效[182-完成]
    SKILL_FLYING_OBJECT(0x9),
    UNK_A(0xA),
    // 使用護身符[182-完成]
    USE_AMULET(0xB),
    UNK_C(0xC),
    MULUNG_DOJO_UP(0xD),
    //職業變更[182-完成]
    CHANGE_JOB(0xE),
    //任務完成[182-完成]
    QUET_COMPLETE(0xF),
    // 回復特效[182-完成]
    HEAL_EFFECT(0x10),
    UNK_11(0x11),
    UNK_12(0x12),
    UNK_13(0x13),
    REWARD_ITEM(0x14),
    ITEM_LEVEL_UP(0x15),
    ITEM_MAKER_SUCCESS(0x16),
    DODGE_CHANCE(0x17),
    //顯示WZ的效果[178-完成]
    WZ(0x18),
    UNK_19(0x19),
    UNK_1A(0x1A),
    UNK_1B(0x1B),
    //顯示WZ的效果2[182-完成]
    WZ_NEW(0x1C),
    UNK_1D(0x1D),
    UNK_1E(0x1E),
    UNK_1F(0x1F),
    //商城道具效果[178-完成]
    CASH_ITEM(0x20),
    //恢復HP效果[178-完成]
    HP_HEALED(0x21),
    UNK_22(0x22),
    CHAMPION(0x23),
    UNK_24(0x24),
    UNK_25(0x25),
    UNK_26(0x26),
    UNK_27(0x27),
    UNK_28(0x28),
    UNK_29(0x29),
    UNK_2A(0x2A),
    UNK_2B(0x2B),   
    CRAFTING(0x2C),
    UNK_2D(0x2D),
    UNK_2E(0x2E),
    UNK_2F(0x2F),
    UNK_30(0x30),
    UNK_31(0x31),
    UNK_32(0x32),
    UNK_33(0x33),
    UNSEAL_BOX(0x34),
    DUBL_START(0x35),
    UNK_36(0x36),
    RECHARGE(0x37),
    UNK_38(0x38),
    UNK_39(0x39),
    UNK_3A(0x3A),
    UNK_3B(0x3B),
    UNK_3C(0x3C),
    UNK_3D(0x3D),
    UNK_3E(0x3E),
    UNK_3F(0x3F),
    UNK_40(0x40),
    UNK_41(0x41),
    UNK_42(0x42),
    UNK_43(0x43),
    //暗夜行者技能特效[178-完成]
    FIRE_EFFECT(0x44),
    UNK_45(0x45),
    UNK_46(0x46),
    UNK_47(0x47),
    UNK_48(0x48),
    UNK_49(0x49),
    UNK_4A(0x4A),
    UNK_4B(0x4B),
    ;
    private final int value;
    private SpecialEffectType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
