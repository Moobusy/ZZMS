/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import tools.EncodingDetect;

/**
 *
 * @author Pungin
 */
public enum RecvPacketOpcode implements WritableIntValueHolder {
    
    // 第一個OP未知
    UNKNOWN_FIRST(101),

    // 未知 [01 00 00 00 00 00 00 00 00]
    STRANGE_DATA,
    // 客戶端驗證
    CLIENT_HELLO(1),

    // 密碼驗證
    LOGIN_PASSWORD,
    // 角色選單
    CHARLIST_REQUEST(2),

    // 玩家登入
    PLAYER_LOGGEDIN,
    // 選擇角色
    CHAR_SELECT(2),

    // 伺服器選單回覆
    SERVERLIST_REQUEST,    
    // 自動登入轉向
    LOGIN_REDIRECTOR,
    // 檢查角色名稱
    CHECK_CHAR_NAME(8), 

    // 建立角色
    CREATE_CHAR,
    // 50等角色卡角色建立
    CREATE_LV50_CHAR,
    // 建立終極冒險家
    CREATE_ULTIMATE,
    // 刪除角色
    DELETE_CHAR(5),

    // 客戶端錯誤信息回覆
    CLIENT_FEEDBACK(9),

    // 打工系统
    PART_TIME_JOB,
    // 角色卡
    CHARACTER_CARD,
    // 未知
    ENABLE_LV50_CHAR(2),

    // Pong
    PONG(1),

    // 客戶端錯誤【[ Name: %s, Job: %d, Field: %d, World: %d, Channel: %d ]\r\n】
    CLIENT_ERROR(6),

    // 選擇性別
    SET_GENDER(1),

    // 伺服器狀態
    SERVERSTATUS_REQUEST,
    // 背景驗證
    GET_SERVER(4),

    // 客戶端開始(顯示視窗)
    CLIENT_START(2),
    // 變更地圖
    CHANGE_MAP,
    // 變更頻道
    CHANGE_CHANNEL(3),

    // 購物商城
    ENTER_CASH_SHOP,
    // PvP開始
    ENTER_PVP(1),

    // 阿斯旺開始
    ENTER_AZWAN,
    // 阿斯旺活動
    ENTER_AZWAN_EVENT,
    // 離開阿斯旺
    LEAVE_AZWAN,
    // PvP隊伍
    ENTER_PVP_PARTY,
    // 離開PvP
    LEAVE_PVP(2),

    // 玩家移動
    MOVE_PLAYER,    
    // 取消椅子
    CANCEL_CHAIR,
    // 使用椅子
    USE_CHAIR(1),

    // 近距離攻擊
    CLOSE_RANGE_ATTACK,
    // 遠距離攻擊
    RANGED_ATTACK,
    // 魔法攻擊
    MAGIC_ATTACK,
    // 被動攻擊(抗壓...)
    PASSIVE_ATTACK(2),

    // 角色受傷
    TAKE_DAMAGE,
    // PvP攻擊
    PVP_ATTACK,
    // 普通聊天
    GENERAL_CHAT,
    // 關閉黑板
    CLOSE_CHALKBOARD,
    // 臉部情緒
    FACE_EXPRESSION,
    // 機器人臉部情緒
    FACE_ANDROID,
    // 使用物品效果
    USE_ITEMEFFECT,
    // 使用原地復活
    WHEEL_OF_FORTUNE,
    // 使用稱號效果
    USE_TITLE(1),

    // 變更天使破壞者外觀
    ANGELIC_CHANGE(7),

    // Npc交談
    NPC_TALK(1),

    // Npc詳細交談
    NPC_TALK_MORE,
    // Npc商店
    NPC_SHOP,
    // 倉庫
    STORAGE,
    // 精靈商人
    USE_HIRED_MERCHANT,
    // 精靈商人物品
    MERCH_ITEM_STORE,
    // 宅配操作
    PACKAGE_OPERATION,
    // 取消開放通道
    MECH_CANCEL(5),

    // 智慧貓頭鷹(5230000)
    OWL,
    // 智慧貓頭鷹購買
    OWL_WARP(1),

    // 管理員商店
    ADMIN_SHOP,
    // 向上整理
    ITEM_SORT,
    // 種類排序
    ITEM_GATHER,
    // 物品移動
    ITEM_MOVE(1),
    // +1【輸入觀戰板內容】

    // 移動道具至背包欄位
    MOVE_BAG,
    // 背包道具至道具欄位
    SWITCH_BAG(1),

    // 使用物品
    USE_ITEM,
    // 取消物品效果
    CANCEL_ITEM_EFFECT(1),

    // 使用召喚包(2100017)
    USE_SUMMON_BAG,
    // 使用寵物食品
    PET_FOOD,
    // 提神飲料
    USE_MOUNT_FOOD,
    // 使用腳本物品
    USE_SCRIPTED_NPC_ITEM,
    // 使用製作書
    USE_RECIPE(3),

    // 使用商城道具
    USE_CASH_ITEM,
    // 使用附加潛能印章
    USE_ADDITIONAL_ITEM,
    // 是否允許寵物拾取道具
    ALLOW_PET_LOOT,
    // 是否允許寵物自動餵食
    ALLOW_PET_AOTO_EAT,
    // 使用捕捉道具
    USE_CATCH_ITEM(2),

    // 使用技能書
    USE_SKILL_BOOK(3),

    // 經驗瓶(2230000)
    USE_EXP_POTION(5),

    // 智慧貓頭鷹開始搜索
    USE_OWL_MINERVA,
    // 使用瞬移之石
    USE_TELE_ROCK,
    // 使用回家卷軸
    USE_RETURN_SCROLL,    
    // 移動至梅斯特鎮
    MOVE_ARDENTMILL,    
    // 使用卷軸
    USE_UPGRADE_SCROLL,
    // 使用卷軸保護卡(5064300)
    USE_FLAG_SCROLL,
    // 使用裝備強化卷軸
    USE_EQUIP_SCROLL(3),

    // 使用潛能賦予卷軸
    USE_POTENTIAL_SCROLL,
    // 使用附加潛在能力賦予卷軸
    USE_BONUS_POTENTIAL_SCROLL,
    // 使用烙的印章(2049500)
    USE_CARVED_SEAL,
    // 使用奇怪的方塊(2710000)
    USE_CRAFTED_CUBE(1),

    // 靈魂卷軸
    USE_SOUL_ENCHANTER,
    // 靈魂寶珠
    USE_SOUL_SCROLL,
    // 咒語的痕跡
    EQUIPMENT_ENCHANT(1),

    // 使用背包
    USE_BAG,
    // 使用放大鏡
    USE_MAGNIFY_GLASS(4),

    // 使用能力點數
    DISTRIBUTE_AP,
    // 自動分配能力點數
    AUTO_ASSIGN_AP(1),

    // 自動恢復HP/MP
    HEAL_OVER_TIME(2),
    // +1 [Int][Long][Short][Short]
    // +2

    // 使用技能點數
    DISTRIBUTE_SP,
    // 角色使用技能
    SPECIAL_SKILL,
    // 取消輔助效果
    CANCEL_BUFF,
    // 技能效果
    SKILL_EFFECT,
    // 楓幣掉落
    MESO_DROP,
    // 添加名聲
    GIVE_FAME(1),

    // 角色信息
    CHAR_INFO_REQUEST,
    // 召喚寵物
    SPAWN_PET(1),

    // 取消異常效果
    CANCEL_DEBUFF,
    // 腳本地圖
    CHANGE_MAP_SPECIAL,
    // 使用時空門
    USE_INNER_PORTAL(1),

    // 使用瞬移之石
    TROCK_ADD_MAP,
    // 使用測謊機
    LIE_DETECTOR,
    // 測謊機技能
    LIE_DETECTOR_SKILL,
    // 確認測謊機驗證碼 
    LIE_DETECTOR_RESPONSE,
    // 重新整理測謊機驗證碼
    LIE_DETECTOR_REFRESH,
    // 舉報玩家
    REPORT,
    // 任務操作
    QUEST_ACTION,
    // 重新領取勳章
    REISSUE_MEDAL,
    // 輔助效果回應
    BUFF_RESPONSE(5),

    // 技能組合
    SKILL_MACRO(1),

    // 獎勵道具
    REWARD_ITEM(3),

    // 鍛造技能
    ITEM_MAKER,
    // 全部修理(勇士之村(辛德))
    REPAIR_ALL,
    // 裝備修理
    REPAIR(2),

    // 請求跟隨()
    FOLLOW_REQUEST(1),

    // 組隊任務獎勵
    PQ_REWARD,
    // 請求跟隨回覆
    FOLLOW_REPLY,
    // 自動跟隨回覆()
    AUTO_FOLLOW_REPLY,
    // 能力值信息
    PROFESSION_INFO,
    // 使用培養皿
    USE_POT,
    // 清理培養皿
    CLEAR_POT,
    // 餵食培養皿
    FEED_POT,
    // 治癒培養皿
    CURE_POT,
    // 培養皿獎勵
    REWARD_POT,
    // 阿斯旺復活
    AZWAN_REVIVE(1),

    // 使用髮型卷[2540000]
    USE_COSMETIC(13),

    // DF連擊[意志之劍取消]
    DF_COMBO(1),

    // 神之子狀態轉換
    ZERO_STAT_CHANGE,
    // 神之子
    ZERO_CLOTHES,
    // 使用能力傳播者
    INNER_CIRCULATOR,
    // PvP重生
    PVP_RESPAWN(2),

    // 管理員聊天
    ADMIN_CHAT,
    // 隊伍聊天
    PARTYCHAT,
    // 悄悄話
    COMMAND,
    // 聊天招待
    MESSENGER,
    // 玩家互動
    PLAYER_INTERACTION,
    // 隊伍操作
    PARTY_OPERATION,
    // 接受/拒絕組隊邀請
    DENY_PARTY_REQUEST,
    // 允許組隊邀請
    ALLOW_PARTY_INVITE,
    // 建立遠征隊
    EXPEDITION_OPERATION,
    // 遠征隊搜尋
    EXPEDITION_LISTING,
    // 公會操作
    GUILD_OPERATION,
    // 拒絕公會邀請
    DENY_GUILD_REQUEST,
    // 申請加入公會
    JOIN_GUILD_REQUEST,
    // 取消加入公會
    JOIN_GUILD_CANCEL,
    // 允許加入公會邀請
    ALLOW_GUILD_JOIN,
    // 拒絕加入公會邀請
    DENY_GUILD_JOIN(2),

    // 管理員指令
    ADMIN_COMMAND,
    // 管理員指令
    ADMIN_COMMAND2,
    // 管理員日誌
    ADMIN_LOG,
    // 好友操作
    BUDDYLIST_MODIFY(4), 

    // 訊息操作
    NOTE_ACTION(1),

    // 使用時空門
    USE_DOOR,
    // 使用開放通道
    USE_MECH_DOOR(1),

    // 變更鍵盤設置
    CHANGE_KEYMAP,
    // 猜拳遊戲
    RPS_GAME(15),

    // 戒指操作
    RING_ACTION,
    // 結婚操作
    WEDDING_ACTION,
    // 公會聯盟操作
    ALLIANCE_OPERATION,
    // 拒絕公會聯盟邀請
    DENY_ALLIANCE_REQUEST,
    // 與 狂狼/皇家騎士團 嚮導時召喚的NPC對話
    CYGNUS_SUMMON(1),

    // 狂郎勇士連擊
    ARAN_COMBO,
    // 怪物CRC Key改變回傳
    MONSTER_CRC_KEY,
    // 製作道具完成
    CRAFT_DONE,
    // 製作道具效果
    CRAFT_EFFECT,
    // 製作道具開始
    CRAFT_MAKE(7),

    // 變更房間
    CHANGE_ROOM_CHANNEL(1),

    // 選擇技能
    CHOOSE_SKILL,
    // 技能竊取
    SKILL_SWIPE,
    // 檢視技能
    VIEW_SKILLS,
    // 撤銷偷竊技能
    CANCEL_OUT_SWIPE(6),
    
    // 釋放意志之劍
    RELEASE_TEMPEST_BLADES(6),
    
    // 更新超級技能(Done)
    UPDATE_HYPER,
    // 重置超級技能(Done)
    RESET_HYPER(9),
    
    // 被怪物抓到
    MONSTER_BAN(6),
    
    // 返回選角界面
    BACK_TO_CHARLIST(14),
    
    // 創建角色跟刪除角色輸入的驗證碼
    SECURITY_CODE(1),

    // 更新烈焰溜溜球個數
    PINKBEAN_YOYO_REQUEST,
    // 快速移動(非打開NPC)
    QUICK_MOVE_SPECIAL(9),
    
    // 幸運怪物(完成)
    LUCKY_LUCKY_MONSTORY(6),

    // 神之子鏡子世界地圖傳送
    ZERO_QUICK_MOVE(9),

    // 活動卡片
    EVENT_CARD,
    // 凱撒快速鍵(176-Done)
    KAISER_QUICK_KEY(3),

    // 賓果
    BINGO(12),

    // 燃燒計畫
    COMBUSTION_PROJECT,    
    // 變更角色順序
    CHANGE_CHAR_POSITION,
    // 創角進入遊戲
    CREACTE_CHAR_SELECT(1),

    // 寵物移動
    MOVE_PET,
    // 寵物說話
    PET_CHAT,
    // 寵物指令
    PET_COMMAND,
    // 寵物拾取
    PET_LOOT,
    // 寵物自動吃藥
    PET_AUTO_POT,
    // 寵物_除外道具
    PET_IGNORE,
    // 寵物自動吃食品
    PET_AUTO_FOOD(3),

    // 花狐移動
    MOVE_HAKU,
    // 花狐動作(包括變身)
    HAKU_ACTION(2),

    // 影朋花狐使用輔助技能
    HAKU_USE_BUFF(2),

    //召唤兽移动
    MOVE_SUMMON,
    //召唤兽攻击(176.Done)
    SUMMON_ATTACK,
    //召唤兽伤害(176.Done)
    DAMAGE_SUMMON,
    //召唤兽技能(176.Done)
    SUB_SUMMON,
    //移除召唤兽(176.Done)
    REMOVE_SUMMON(5),

    //神龍移動
    MOVE_DRAGON(2),

    // 使用物品任務
    USE_ITEM_QUEST,
    // 機器人移動
    MOVE_ANDROID, 
    //安卓情感回傳(176.Done)
    ANDROID_EMOTION_RESULT,
    //更新任務
    UPDATE_QUEST,
    QUEST_ITEM(3),

    //快速欄按鍵(176.Done)
    QUICK_SLOT,
    //按下按鈕
    BUTTON_PRESSED(1),

    // 操控角色完成反饋
    DIRECTION_COMPLETE(2),

    //進程列表
    SYSTEM_PROCESS_LIST(1),
    
    //神之子-開始強化
    ZERO_SCROLL_START,
    //神之子-武器潛在能力
    ZERO_WEAPON_ABILITY,
    //神之子-武器介面
    ZERO_WEAPON_UI,
    //神之子-與精靈對話
    ZERO_NPC_TALK,
    //神之子-使用卷軸
    ZERO_WEAPON_SCROLL,
    //神之子-武器成長
    ZERO_WEAPON_UPGRADE,
    //神之子-武器成長
    ZERO_WEAPON_UPGRADE_START(3),

    //加載角色成功
    LOAD_PLAYER_SCCUCESS(2),

    // 箭座控制
    ARROW_BLASTER_ACTION(21),
    
    // 遊戲嚮導
    GUIDE_TRANSFER(5),

    // 新星世界
    SHINING_STAR_WORLD,
    // Boss清單
    BOSS_LIST(33),

    // 公會佈告欄操作
    BBS_OPERATION(4),

    // 離開遊戲 
    EXIT_GAME(1),

    // 潘姆音樂
    PAM_SONG(24),

    // 聖誕團隊藥水[2212000]
    TRANSFORM_PLAYER(1),
    // +1 [Long]

    // 進擊的巨人視窗選項反饋
    ATTACK_ON_TITAN_SELECT(1),

    // 拍賣系統
    ENTER_MTS,
    // 使用兵法書(2370000)
    SOLOMON,
    // 獲得兵法書經驗值
    GACH_EXP(13),

    // 使用強化任意門
    CHRONOSPHERE(3),

    // 使用閃耀方塊(5062017)
    USE_FLASH_CUBE(31),

    // 怪物移動
    MOVE_LIFE,
    // 怪物復仇
    AUTO_AGGRO,
    // 怪物自爆
    MONSTER_BOMB(22),

    // Npc動作(包括說話)
    NPC_ACTION(6),

    // 拾取物品
    ITEM_PICKUP(2),

    // 攻擊箱子
    DAMAGE_REACTOR,
    // 雙擊箱子
    TOUCH_REACTOR(3),

    // 召喚分解機
    MAKE_EXTRACTOR,
    // 玩家數據更新?
    UPDATE_ENV(2),

    // 滾雪球
    SNOWBALL,
    // 向左擊飛
    LEFT_KNOCK_BACK(18),

    // 玩家更新
    PLAYER_UPDATE,
    //组队成员搜索
    MEMBER_SEARCH,
    //队伍搜索
    PARTY_SEARCH(5),

    // 開始採集
    START_HARVEST,
    // 停止採集
    STOP_HARVEST(2),

    // 快速移動(開啟Npc)
    QUICK_MOVE,
    //採集符文輪
    TOUCH_RUNE,
    //取得符文
    USE_RUNE(123),

    // 購物商城更新
    CS_UPDATE,
    // 購買點數道具
    BUY_CS_ITEM,
    // 使用兌換券
    COUPON_CODE(1),

    // 購物商城送禮
    CS_GIFT(1),

    // 儲存造型設計
    CASH_CATEGORY(8),

    // 創建角色二次密碼認證
    CREATE_CHAR_2PW(14),

    // 使用黃金鐵鎚
    GOLDEN_HAMMER,
    // 黃金鐵鎚使用完成
    VICIOUS_HAMMER(1),
    
    // 使用白金鎚子
    PLATINUM_HAMMER(68),
    
    // 獲得獎勵
    REWARD(5),
    
    // 裝備特效開關
    EFFECT_SWITCH,


    // 未知OPS，不繼續增加
    UNKNOWN,
    
    // 使用世界樹的祝福(2048500)
    USE_ABYSS_SCROLL,
    MONSTER_BOOK_DROPS,
    
    // General
    RSA_KEY,
    MAPLETV,
    CRASH_INFO,
    // Login
    GUEST_LOGIN,
    TOS,
    VIEW_SERVERLIST,
    REDISPLAY_SERVERLIST,
    CHAR_SELECT_NO_PIC,
    AUTH_REQUEST,
    VIEW_REGISTER_PIC,
    VIEW_SELECT_PIC,
    CLIENT_FAILED,
    ENABLE_SPECIAL_CREATION,
    CREATE_SPECIAL_CHAR,
    AUTH_SECOND_PASSWORD,
    WRONG_PASSWORD,
    ENTER_FARM,
    CHANGE_CODEX_SET,
    CODEX_UNK,

    USE_NEBULITE,
    USE_ALIEN_SOCKET,
    USE_ALIEN_SOCKET_RESPONSE,
    USE_NEBULITE_FUSION,

    TOT_GUIDE,

    GET_BOOK_INFO,
    USE_FAMILIAR,
    SPAWN_FAMILIAR,
    RENAME_FAMILIAR,
    PET_BUFF,
    USE_TREASURE_CHEST,
    SOLOMON_EXP,
    NEW_YEAR_CARD,
    XMAS_SURPRISE,
    TWIN_DRAGON_EGG,
    YOUR_INFORMATION,
    FIND_FRIEND,
    PINKBEAN_CHOCO_OPEN,
    PINKBEAN_CHOCO_SUMMON,
    BUY_SILENT_CRUSADE,
    CASSANDRAS_COLLECTION,
    BUDDY_ADD,
    //HAKU_1D8,
    //HAKU_1D9,
    PVP_SUMMON,

    MOVE_FAMILIAR,
    TOUCH_FAMILIAR,
    ATTACK_FAMILIAR,
    REVEAL_FAMILIAR,

    FRIENDLY_DAMAGE,
    HYPNOTIZE_DMG,

    MOB_BOMB,
    MOB_NODE,
    DISPLAY_NODE,
    MONSTER_CARNIVAL,

    CLICK_REACTOR,
    CANDY_RANKING,
    COCONUT,
    SHIP_OBJECT,
    PLACE_FARM_OBJECT,
    FARM_SHOP_BUY,
    FARM_COMPLETE_QUEST,
    FARM_NAME,
    HARVEST_FARM_BUILDING,
    USE_FARM_ITEM,
    RENAME_MONSTER,
    NURTURE_MONSTER,
    EXIT_FARM,
    FARM_QUEST_CHECK,
    FARM_FIRST_ENTRY,
    PYRAMID_BUY_ITEM,
    CLASS_COMPETITION,
    MAGIC_WHEEL,
    BLACK_FRIDAY,
    RECEIVE_GIFT_EFFECT,
    UPDATE_RED_LEAF,
    //Not Placed:
    SPECIAL_STAT,

    DRESSUP_TIME,
    OS_INFORMATION,
    LUCKY_LOGOUT,
    MESSENGER_RANKING;

    @Override
    public void setValue(short code) {
        this.code = code;
    }

    @Override
    public final short getValue() {
        if (code != 0x7FFE) {
            return code;
        }

        short i = -1;
        for (RecvPacketOpcode op : RecvPacketOpcode.values()) {
            if (UNKNOWN == op) {
                // UNKNOWN之後的為未知OPS，直接返回默認code值
                return code;
            }
            i++;
            if (op == this) {
                return i;
            }
            i += op.getNextSkip();
        }
        return i;
    }

    private short code = 0x7FFE;
    private short nextSkip = 0;

    private RecvPacketOpcode() {
    }

    private RecvPacketOpcode(final int nextSkip) {
        this.nextSkip = (short) nextSkip;
    }

    public void setNextSkip(short code) {
        this.nextSkip = code;
    }

    public final short getNextSkip() {
        return nextSkip;
    }

    public static String nameOf(short value) {
        for (RecvPacketOpcode header : RecvPacketOpcode.values()) {
            if (header.getValue() == value) {
                return header.name();
            }
        }
        return "UNKNOWN";
    }

    public static boolean isSpamHeader(RecvPacketOpcode header) {
        switch (header.name()) {
            case "PONG":
            case "NPC_ACTION":
//            case "ENTER"":
//            case "CRASH_INFO":
//            case "AUTH_REQUEST":
//            case "SPECIAL_MOVE":
            case "MOVE_LIFE":
            case "MOVE_PLAYER":
            case "MOVE_ANDROID":
//            case "MOVE_DRAGON":
            case "MOVE_SUMMON":
//            case "MOVE_FAMILIAR":
//            case "MOVE_PET":
            case "AUTO_AGGRO":
//            case "CLOSE_RANGE_ATTACK":
//            case "QUEST_ACTION":
            case "HEAL_OVER_TIME":
//            case "CHANGE_KEYMAP":
//            case "USE_INNER_PORTAL":
//            case "MOVE_HAKU":
//            case "FRIENDLY_DAMAGE":
//            case "CLOSE_RANGE_ATTACK":
//            case "RANGED_ATTACK":
//            case "ARAN_COMBO":
//            case "SPECIAL_STAT":
//            case "UPDATE_HYPER":
//            case "RESET_HYPER":
//            case "ANGELIC_CHANGE":
//            case "DRESSUP_TIME":
            case "BUTTON_PRESSED":
            case "STRANGE_DATA":
            case "SYSTEM_PROCESS_LIST":
            case "PINKBEAN_YOYO_REQUEST":
                return true;
            default:
                return false;
        }
    }

    public static void loadValues() {
        Properties props = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("recvops.properties")) {
            props.load(new BufferedReader(new InputStreamReader(fileInputStream, EncodingDetect.getJavaEncode("recvops.properties"))));
        } catch (IOException ex) {
            InputStream in = RecvPacketOpcode.class.getClassLoader().getResourceAsStream("recvops.properties");
            if (in == null) {
                System.out.println("未加載 recvops.properties 檔案, 使用內置RecvPacketOpcode Enum");
                return;
            }
            try {
                props.load(in);
            } catch (IOException e) {
                throw new RuntimeException("加載 recvops.properties 檔案出現錯誤", e);
            }
        }
        ExternalCodeTableGetter.populateValues(props, values());
    }

    static {
        loadValues();
    }
}
