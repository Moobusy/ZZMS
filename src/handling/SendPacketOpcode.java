package handling;

import constants.ServerConfig;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import tools.EncodingDetect;
import tools.FileoutputUtil;
import tools.StringUtil;

public enum SendPacketOpcode implements WritableIntValueHolder {

    //================================
    // CLogin::OnPacket 開始(183-完成)
    //================================ 
    // 密碼驗證
    LOGIN_STATUS((short) 0x00),
    // 伺服器選單
    SERVERLIST((short) 0x01),
    // CLogin::OnLatestConnectedWorld
    ENABLE_RECOMMENDED((short) 0x02), 
    // CLogin::OnRecommendWorldMessage
    SEND_RECOMMENDED((short) 0x03),
    
    // 0x04 [Long] * 8
    
    // 選擇伺服器 [Int(伺服器編號)]
    CHANNEL_SELECTED((short) 0x05),
    // 角色選單
    CHARLIST((short) 0x06),
    // 伺服器IP
    SERVER_IP((short) 0x07),    
    // 帳號信息
    ACCOUNT_INFO((short) 0x08),
    
    // 0x09 [Byte]
    
    // 檢查角色名稱
    CHAR_NAME_RESPONSE((short) 0x0A),
    // 建立角色
    ADD_NEW_CHAR_ENTRY((short) 0x0B),
    // 刪除角色
    DELETE_CHAR_RESPONSE((short) 0x0C),

    // 0x0D 【刪除新星世界角色】[Int][Boolean] false => [Long][Long]
    // 0x0E 【登入新星世界伺服器(未確認)】[Int][Byte]
    // 0x0F 【變更角色名稱】[Int][String][Short]
    // 0x10 [Int]

    // 變更頻道
    CHANGE_CHANNEL((short) 0x11),
    // Ping
    PING((short) 0x12),
    
    // 0x13
    
    // 購物商城
    CS_USE((short) 0x14),
    
    // 0x15 CClientSocket::OnAuthenMessage【驗證訊息】[Int][Byte]
    // 0x16
    // 0x17 【外掛偵測】
    // 0x18
    // 0x19 【身分驗證】[Byte]
    // 0x1A
    // 0x1B CClientSocket::OnCheckCrcResult【外掛偵測】[Boolean]

    // 打工系統
    PART_TIME((short) 0x1C),
    
    // 0x1D [-]
    // 0x1E [-]
    // 0x1F [Int][Int]
    // 0x20 【購買道具】[Int][Int][Int][Byte][Byte][Byte]
    
    // 選擇性別 + 設置第二組密碼
    CHOOSE_GENDER((short) 0x21),
    // 選擇性別 + 設置第二組密碼回覆
    GENDER_SET((short) 0x22),
    // 外掛偵測
    HACKSHIELD_REQUEST((short) 0x23),
    // 強制變更角色名稱 [-]
    FORCED_CHANGE_CHAR_NAME((short) 0x24),
    // 強制變更角色名稱訊息 [Byte]
    FORCED_CHANGE_CHAR_NAME_NOTICE((short) 0x25), 
    // 伺服器狀態
    SERVERSTATUS((short) 0x26),
    // 背景驗證
    LOGIN_AUTH((short) 0x27),
    
    // 0x28
    // 0x29
    // 0x2A [Byte][Byte][Int]
    // 0x2B 【新星世界按鈕 + 刪除角色按鈕】[Int]
    
    // 顯示視窗
    SHOW_MAPLESTORY((short) 0x2C),
    // 第二組密碼錯誤
    SECONDPW_ERROR((short) 0x2D),
    
    //================================
    // CMapLoadable::OnPacket 開始(183-完成)
    //================================ 
    
    // 移除BG層[完成]
    REMOVE_BG_LAYER((short) 0x2E),
    // 變更背景[完成]
    CHANGE_BACKGROUND((short) 0x2F),
    // 設置物件狀態
    SET_MAP_OBJECT_VISIBLE((short) 0x30),
    
    // 0x31
    
    // 重置畫面[完成]
    RESET_SCREEN((short) 0x32),

    // 0x31
    // 到
    // 0x3C
    
    //================================
    // CWvsContext::OnPacket 開始(183-完成)
    //================================    
    // 道具操作[完成]
    INVENTORY_OPERATION((short) 0x3D),
    // 擴充道具欄位[完成]
    INVENTORY_GROW((short) 0x3E),
    // 更新能力值[完成]
    UPDATE_STATS((short) 0x3F),
    // 獲得輔助效果[完成]
    GIVE_BUFF((short) 0x40),
    // 取消輔助效果[完成]
    CANCEL_BUFF((short) 0x41),
    // 臨時能力值開始[完成]
    TEMP_STATS((short) 0x42),
    // 重置臨時能力值[完成]
    TEMP_STATS_RESET((short) 0x43),
    // 更新技能[完成]
    UPDATE_SKILLS((short) 0x44),
    // 幻影俠盜複製技能成功
    UPDATE_STOLEN_SKILLS((short) 0x45),
    // 幻影俠盜竊取技能時顯示 
    TARGET_SKILL((short) 0x46),
    
    // 0x47
    // 0x48
    // 0x49
    // 0x4A
    
    // 偷竊技能檢查(Done)
    STEEL_SKILL_CHECK((short) 0x4B),
    
    // 0x4C
    // 惡魔追蹤發送
    CONVEY_TO((short) 0x4D),
    // 名聲回覆[完成]
    FAME_RESPONSE((short) 0x4E),
    // 顯示角色狀態信息[完成]
    SHOW_STATUS_INFO((short) 0x4F),
    // 訊息[完成]
    SHOW_NOTES((short) 0x50),
    // 瞬移之石[完成]
    TROCK_LOCATIONS((short) 0x51),
    // 測謊機[完成]
    LIE_DETECTOR((short) 0x52),
    // 炸彈測謊機[完成]
    BOMB_LIE_DETECTOR((short) 0x53),
    
    // 0x54 (null)
    
    // 設置舉報[完成]
    REPORT_RESPONSE((short) 0x55),
    // 舉報時間[完成]
    REPORT_TIME((short) 0x56),
    // 舉報狀態[完成]
    REPORT_STATUS((short) 0x57),
    
    // 0x58 [Int] [do { [Int][Int] }] [176+]
    
    // 更新騎寵[完成]
    UPDATE_MOUNT((short) 0x59),
    // 任務完成[完成]
    SHOW_QUEST_COMPLETION((short) 0x5A),
    // 精靈商人[完成]
    SEND_TITLE_BOX((short) 0x5B),
    // 使用技能書[完成]
    USE_SKILL_BOOK((short) 0x5C),
    // 重置SP[完成]
    SP_RESET((short) 0x5D),
    // 重置AP[完成]
    AP_RESET((short) 0x5E),
    // 經驗瓶[完成]
    EXP_POTION((short) 0x5F),
    // 散佈道具[完成] [Byte][Int][Byte]
    DISTRIBUTE_ITEM((short) 0x60),
    // 擴充角色欄位[完成]
    EXPAND_CHARACTER_SLOTS((short) 0x61),
    // 申請變更角色名稱[完成]
    APPLY_NAME_CHANGE((short) 0x62),
    // 向上整理[完成]
    FINISH_SORT((short) 0x63),
    // 種類排序[完成]
    FINISH_GATHER((short) 0x64),
    
    // 0x65 (null)
    // 0x66 (null)
    
    // 角色信息[完成]
    CHAR_INFO((short) 0x67),
    // 隊伍操作[完成]
    PARTY_OPERATION((short) 0x68),
    // 尋找隊員
    MEMBER_SEARCH((short) 0x69),
    // 隊伍搜尋
    PARTY_SEARCH((short) 0x6A),
    
    // 0x6B
    // 0x6C
    // 0x6D (null)
    
    // 遠征隊操作
    EXPEDITION_OPERATION((short) 0x6E),
    // 好友列表[完成]
    BUDDYLIST((short) 0x6F),
    
    // 0x70 [176+]
    // 0x71 [176+]
    
    // 請求回覆[完成]
    GUILD_REQUEST((short) 0x72),
    // 公會操作[完成]
    GUILD_OPERATION((short) 0x73),
    // 公會聯盟操作[完成]
    ALLIANCE_OPERATION((short) 0x74),
    // 時空門[完成]
    SPAWN_PORTAL((short) 0x75),
    // 開放通道[完成]
    MECH_PORTAL((short) 0x76),
    // 伺服器訊息[完成]
    SERVERMESSAGE((short) 0x77),
    // 阿斯旺海防戰訊息[完成]
    AZWAN_MSG((short) 0x78),
    // 花生機獎勵[完成]
    PIGMI_REWARD((short) 0x79),
    // 獲得道具[完成]
    ITEM_OBTAIN((short) 0x7A),
    // 智慧貓頭鷹[完成]
    OWL_OF_MINERVA((short) 0x7B),
    // 智慧貓頭鷹回覆
    OWL_RESULT((short) 0x7C),
    
    // 0x7D (null)
    // 0x7E (null)
    
    // 戒指操作請求[完成]
    ENGAGE_REQUEST((short) 0x7F),
    // 戒指操作返回[完成]
    ENGAGE_RESULT((short) 0x80),
    // 結婚禮物[完成]
    WEDDING_GIFT((short) 0x81),
    // 結婚地圖變更[完成] [Int][Int]
    WEDDING_MAP_TRANSFER((short) 0x82),
    // 使用寵物飼料[完成]
    USE_CASH_PET_FOOD((short) 0x83),
    // 使用寵物技能[完成]
    USE_CASH_PET_SKILL((short) 0x84),
    
    // 0x85 [-]
    
    // 神秘的鐵砧[完成]
    FUSION_ANVIL((short) 0x86),
    
    // 0x87 [Byte][Long]
    
    // 黃色公告[完成]
    YELLOW_CHAT((short) 0x88),
    // 商店優惠[完成]
    SHOP_DISCOUNT((short) 0x89),
    // 捕捉怪物[完成]
    CATCH_MOB((short) 0x8A),
    // 建立玩家Npc[完成] [Byte]
    MAKE_PLAYER_NPC((short) 0x8B),
    // 玩家Npc[完成]
    PLAYER_NPC((short) 0x8C),
    
    // 0x8D(null)
    
    // 隱藏Npc[完成]
    DISABLE_NPC((short) 0x8E),
    // 獲得卡片[完成]
    GET_CARD((short) 0x8F),
    // 卡片設置[完成] [Int]
    CARD_SET((short) 0x90),
    // 變更小時[完成]
    CHANGE_HOUR((short) 0x91),
    // 重置小地圖[完成]
    RESET_MINIMAP((short) 0x92),
    // 教師更新[完成]
    CONSULT_UPDATE((short) 0x93),
    // 班級更新[完成]
    CLASS_UPDATE((short) 0x94),
    // 網頁瀏覽更新[完成]
    WEB_BOARD_UPDATE((short) 0x95),
    // 擊殺數量[完成]
    SESSION_VALUE((short) 0x96),
    // 組隊數值[完成]
    PARTY_VALUE((short) 0x97),
    // 地圖數值[完成]
    MAP_VALUE((short) 0x98),
    
    // 0x99 [String][String]
    
    // 精靈墜飾[完成]
    EXP_BONUS((short) 0x9A),
    // 家族系統[已關閉][完成]
    SEND_PEDIGREE((short) 0x9B),
    OPEN_FAMILY((short) 0x9C),
    FAMILY_MESSAGE((short) 0x9D),
    FAMILY_INVITE((short) 0x9E),
    FAMILY_JUNIOR((short) 0x9F),
    SENIOR_MESSAGE((short) 0xA0),
    FAMILY((short) 0xA1),
    REP_INCREASE((short) 0xA2),
    FAMILY_LOGGEDIN((short) 0xA3),
    FAMILY_BUFF((short) 0xA4),
    FAMILY_USE_REQUEST((short) 0xA5),
    // (公會成員)升級訊息[完成]
    LEVEL_UPDATE((short) 0xA6),
    // 结婚訊息[完成]
    MARRIAGE_UPDATE((short) 0xA7),
    // 轉職訊息[完成]
    JOB_UPDATE((short) 0xA8),
    // 項鍊擴充[完成]
    SLOT_UPDATE((short) 0xA9),
    // 請求跟隨提示[完成]
    FOLLOW_REQUEST((short) 0xAA),
    // 新頂部訊息[完成]
    TOP_MSG2((short) 0xAB),
    // 頂部訊息[完成]
    TOP_MSG((short) 0xAC),
    // 新頂部訊息[完成]
    NEW_TOP_MSG((short) 0xAD),
    // 中間訊息[完成]
    MID_MSG((short) 0xAE),
    // 清理中間的訊息[完成]
    CLEAR_MID_MSG((short) 0xAF),
    // 特殊訊息[完成]
    SPECIAL_MSG((short) 0xB0),
    // 182新增的訊息[完成]
    MAPLE182_ADD_MSG((short) 0xB1),
    // 楓之谷提示訊息[完成]
    MAPLE_TIP_MSG((short) 0xB2),
    // 楓之谷管理員訊息[完成]
    MAPLE_ADMIN_MSG((short) 0xB3),
    // 檢查道具欄位[完成]
    INVENTORY_FULL((short) 0xB4),
    // 更新美洲豹[完成]
    UPDATE_JAGUAR((short) 0xB5),
    // 神之子能力值
    ZERO_STATS((short) 0xB6),
    // 神之子更新
    ZERO_UPDATE((short) 0xB7),
    
    // 0xB8
    
    // 終極冒險家[完成]
    ULTIMATE_EXPLORER((short) 0xB9),
    
    // 0xBA
    
    // 能力值信息[完成-182]
    SPECIAL_STAT((short) 0xBB),
    // 更新培養皿時間[完成]
    UPDATE_IMP_TIME((short) 0xBC),
    // 使用培養皿[完成]
    ITEM_POT((short) 0xBD),
    
    // 0xBE
    // 0xBF

    // 武陵道場訊息
    MULUNG_MESSAGE((short) 0xC0),
    // 傳授角色技能
    GIVE_CHARACTER_SKILL((short) 0xC1),
    
    // 0xC2【00 00】
    // 0xC3
    // 0xC4
    // 0xC5
    
    // 武陵排行[完成]
    MULUNG_DOJO_RANKING((short) 0xC6),
    
    // 0xC7
    
    // 更新潛在能力值
    UPDATE_INNER_ABILITY((short) 0xC8),
    
    // 0xC9
    
    // 使用/刪除技能[完成]
    REPLACE_SKILLS((short) 0xCA),
    // 內在能力值訊息[完成]
    INNER_ABILITY_MSG((short) 0xCB),
    // 地圖指引[完成] [Int]
    MINIMAP_ARROW((short) 0xCC),
    
    // 0xCD [Boolean][Int] true => [Int]
    
    // 角色潛在能力設定[完成]
    ENABLE_INNER_ABILITY((short) 0xCE),
    // 角色潛在能力重置
    DISABLE_INNER_ABILITY((short) 0xCF),
    // 獲得名聲值[完成]
    UPDATE_HONOUR((short) 0xD0),
    // 阿斯旺未知[未知]
    AZWAN_UNKNOWN((short) 0xD1),
    // 阿斯旺結果[完成] [Int][Int][Int][Int][Int][Int][Int][Int][Boolean]
    AZWAN_RESULT((short) 0xD2),
    // 阿斯旺擊殺[完成]
    AZWAN_KILLED((short) 0xD3),
    
    // 0xD4 【point】
    // 0xD5 阿斯旺復活[String(玩家名稱)][Int](復活時間)
    
    // 能力傳播者[完成]
    CIRCULATOR_ON_LEVEL((short) 0xD6),
    // 十字獵人訊息[完成]
    SILENT_CRUSADE_MSG((short) 0xD7),
    // 十字獵人商店[完成]
    SILENT_CRUSADE_SHOP((short) 0xD8),
    
    // 0xD9 幸運怪物【UI/UIWindow2.img/mapleMuseum】【UI/UIWindow2.img/mapleMuseum2)(UI/UIWindow2.img/luckyMonstery】
    // 0xDA
    // 0xDB 幸運怪物【UI/UIWindow2.img/mapleMuseum】【UI/UIWindow2.img/mapleMuseum2)(UI/UIWindow2.img/luckyMonstery】
    // 0xDC 【state】
    // 0xDD 【state】
    // 0xDE
    // 自動飛行[完成] [Int]
    AUTO_FLYING((short) 0xDF),
    // 禁止完成任務
    DISALLOW_DELIVERY_QUEST((short) 0xE0),
    // 0xE1 【彈跳視窗】
    // 0xE2 【賓果活動?】 [Byte] 【deck】【bingo】
    // 0xE3 【賓果活動?】 [Byte] 【deck】【bingo】
    // 0xE4
    // 0xE5 【獲取物品?】【---】
    
    // 楓葉點數(完成)
    MAPLE_POINT((short) 0xE6),
    
    // 0xE7 [Long]
    // 0xE8 【方塊洗洗樂?】
    // 0xE9 【重置神聖SP】 [Byte][Int][Byte]
    // 0xEA 【Debug信息?】 [Short](年)[Short](月)[Short](日)[Short](點)[Short](分)[Int]((DWORD)%u, (LONG)%d)【(DWORD)%u, (LONG)%d】
    // 0xEB 【Debug信息?】 [Int][Long]
    // 0xEC
    // 0xED 【沒有可以套用回復效果的對象。】【Recv => 0x176([Int][Byte])】
    // 0xEE
    // 0xEF 【Recv => 0xC9([Int][Short][Long])】
    // 0xF0 【Recv => 0xC9([Int][Short][Long])】
    // 0xF1
    
    // 變成破壞天使【Recv => 0x173([Byte])
    CHANGE_ANGELIC((short) 0xF2),
    // 解鎖充電技能
    UNLOCK_CHARGE_SKILL((short) 0xF3),
    // 上鎖充電技能
    LOCK_CHARGE_SKILL((short) 0xF4),
    //自動登入
    AUTO_LOGIN((short) 0xF5),
    
    // 0xF6 【寵物名牌戒指?】【[BP:%02d] %d】【///////////////////////////////】
    
    // 進化系統[完成]
    EVOLVING_ACTION((short) 0xF7),
    // BossPvP技能【Recv => 0x16D】[完成]
    BOSSPVP_SKILL_UI((short) 0xF8),
    
    // 0xF9
    
    // 公會搜索
    GUILD_SEARCH((short) 0xFA),
    
    // 0xFB
    // 0xFC 【幸運怪物】【UI/UIWindow2.img/mapleMuseum】【UI/UIWindow2.img/mapleMuseum2】【UI/UIWindow2.img/luckyMonstery】【UI/UIWindow2.img/luckyMonResult】
    // 0xFD 【獲取物品?】
    // 0xFE 【10 00 00 00 00 00 00 00 00 00 00 00 00 00 00】
    // 0xFF
    
    // 請求進程列表[完成]
    SYSTEM_PROCESS_LIST((short) 0x100),
    
    // 0x101
    // 0x102
    // 0x103

    // 情景喇叭訊息[完成]
    AVATAR_MEGA_RESULT((short) 0x104),
    // 情景喇叭[完成]
    AVATAR_MEGA((short) 0x105),
    // 移除情景喇叭[完成]
    AVATAR_MEGA_REMOVE((short) 0x106),
    // 活動清單[完成]
    EVENT_LIST((short) 0x107),
    // 楓之谷聊天室
    MESSENGER_OPEN((short) 0x108),
    
    // 0x109 【簽名】
    // 0x10A 【問候玩家】
    
    // 王冠活動[完成-182]
    EVENT_CROWN((short) 0x10B),
    
    // 0x10C [Byte][Byte]
    
    // 自由轉職[完成]
    FREE_CHANGE_JOB((short) 0x10D),
    
    // 0x10E
    // 0x10F
    // 0x110
    // 0x111
    // 0x112
    // 0x113
    // 0x114
    // 0x115
    // 0x116
    // 0x117 【頂部訊息】
    // 0x118 【IP驗證】
    // 0x119 【Etc/CashPackage.img/%d/SN】
    // 0x11A 【獸魔激鬥擂台賽】
    // 0x11B 【UI/UIWindowBT.img/MonsterBattleSelection/num】
    // 0x11C 【任務抵達】
    // 0x11D
    // 0x11E
    // 0x11F
    // 0x120
    // 0x121

    // 開啟墜飾欄(175+)
    UPDATE_PENDANT_SLOT((short) 0x122),
    // 魔王競技場配對成功[完成]
    BOSSPVP_FOUND((short) 0x123),
    // 魔王競技場配對失敗[完成]【Recv => 0x1FD ([Byte])】
    BOSSPVP_FAIL((short) 0x124),
    // 參加魔王競技場配對[完成]
    BOSSPVP_SEARCH((short) 0x125),
    // 0x126
    // 0x127
    // 0x128
    // 0x129
    // 0x12A 【00】
    // 0x12B 【伺服器移民】
    // 0x12C 【擴充倉庫欄位】
    
    // 菁英王訊息[完成]
    ELITE_BOSS_NOTICE((short) 0x12D),
    
    // 0x12E 【00 00 00 00 00 00 00 00 00】
    
    // 咒文的痕跡[完成-182]
    EQUIPMENT_ENCHANT((short) 0x130),
    // The Seed 排行
    TOWER_OF_OZ_RANKING((short) 0x131),
    // The Seed 好友排行
    TOWER_OF_OZ_FRIEND_RANKING((short) 0x132),
    // The Seed 獎勵[完成] [Int](樓層)[Int](時間)[Int](The Seed點數)[Int](獲得經驗值)[Int][Int]
    TOWER_OF_OZ_REWARD((short) 0x133),
    // 0x12F [Int][Int][Byte]
    // 0x130
    // 0x131
    // 0x132
    // 0x133
    // 0x134

    // 離開遊戲[完成]
    EXIT_GAME((short) 0x140),
    // 火步行[未知]
    FIRE_STEP((short) 0x141),
    // 0x137
    // 0x138
    // 0x139 【臉部情緒?】【Unlock request Failed】
    // 0x13A 【Recv => 0x23D ([Byte][Int][String][Byte])】
    // 0x13B 【Item/Cash/0501.img/%08d/effect】
    // 0x13C
    // 0x13D
    // 0x13E
    // 0x13F
    // 0x140
    // 0x141 【彈跳視窗】
    // 申請變更角色名稱[完成]
    NAME_CHANGE((short) 0x142),
    // 雪橇活動[完成]【UI/Sboating.img/Basic/backgrnd】【Recv => 0x243 ([Byte])】
    SELECT_SLEIGH((short) 0x143),
    // 潘姆音樂[完成]
    PAM_SONG((short) 0x144),
    // 餽贈認證[完成-182]
    MAPLE_FEED_AUTHEN((short) 0x14B),
    // 速配指數[完成]
    QUICK_PAIR_RESULT((short) 0x146),
    // 0x147
    // 0x148
    // 0x149
    // 贈送小鋼珠[完成]
    GIFTS_BALL((short) 0x14A),
    // 九龍珠
    DRAGON_BALL((short) 0x14B),
    // 開啟寶箱[完成]
    TREASURE_BOX((short) 0x14C), //[Int] [0:金、1:銀、5:神秘開罐器、6:幻想開罐器]
    // 0x14D
    // 0x14E 【釣魚系統】
    // 0x14F
    // 0x150 【周圍沒有攻擊的怪物。】、【無法連續使用.】
    
    // 管理員警告[完成]
    GM_POLICE((short) 0x151),
    // 新年賀卡[完成]
    NEW_YEAR_CARD((short) 0x152),
    // 隨機變身藥水[完成]
    RANDOM_MORPH((short) 0x153),    
    // 個性文字[完成](5480000)
    DISPOSITION_TEXT((short) 0x154),   
    // 經驗值椅子[完成]
    CHAIR_EXP_MSG((short) 0x155),
    
    // 0x156
    // 0x157
    
    // 變更頻道 + 訊息[完成]
    AUTO_CC_MSG((short) 0x158),
    
    // 0x159
    // 0x15A
    // 0x15B
    // 0x15C [Int][Int]
    // 0x15D
    // 0x15E
    // 0x15F
    
    // 獲得獎勵[完成-182]
    REWARD((short) 0x166),
    
    // 0x161
    // 0x162

    // 閃炫方塊回覆[完成-182]
    SHIMMER_CUBE_RESPONSE((short) 0x169),
    
    // 0x164  178新增
    // 0x165 【任務抵達】
    // 0x166
    // 0x167 【購物商城 => [B0 1A 25 00 00 00 00 00 00 80 59 DA 6B 2E CE 01 80 29 A5 02 EC 33 CE 01 18 00 00 00 98 A3 98 00 99 A3 98 00 9A A3 98 00 9B A3 98 00 24 52 A6 00 25 52 A6 00 76 6F 40 01 77 6F 40 01 78 6F 40 01 79 6F 40 01 20 4A CB 01 22 4A CB 01 2E 4A CB 01 2F 4A CB 01 30 4A CB 01 4E 4A CB 01 4F 4A CB 01 50 4A CB 01 55 4A CB 01 6C 4A CB 01 6D 4A CB 01 6E 4A CB 01 6F 4A CB 01 70 4A CB 01]
    // 0x168 【[BlackListLoadDone] [BlackSize:%d] [sTargetIGNs:%s]】
    // 0x169 【神奇剪刀】
    // 0x16A
    // 0x16B 【組隊任務?】
    // 0x16C 
    // 0x16D 【輸入觀戰板內容】【Recv => 0x95】
    // 0x16E 【UI/UIMiniGame.img/starPlanetResult/backgrnd】
    // 0x16F 【新星世界試穿衣服】
    // 0x170 【等待列表】
    // 0x171
    // 0x172
    // 0x173
    // 0x174 178新增
    // 0x175 178新增
    // 0x176 178新增
    // 0x177 178新增
    // 0x178 178新增
    // 0x179 178新增
    // 0x17A 178新增    
    // 0x17B
    // 0x17C 179新增 
    // 0x17D 179新增
    // 0x17E 179新增
    
    // 咒文的痕跡(FOREVER_TIME)
    FEVER_TIME((short) 0x7FFE), 
    // 0x18E 183新增 RETURN 0
    // 0x18F 183新增 [INT][v4->INT][[INT]*v4]
    // 0x190 183新增 [INT][v4->INT][[INT]*v4]
    // 技能組合[完成]
    SKILL_MACRO((short) 0x191),
    
    //================================
    // CStage::OnPacket 開始(183-完成)
    //================================ 
    
    // 地圖傳送[完成]
    WARP_TO_MAP((short) 0x192),
    // 農場[台版關閉]
    FARM_OPEN((short) 0x193),
    // 拍賣系統[完成]
    MTS_OPEN((short) 0x194),
    // 購物商城[完成]
    CS_OPEN((short) 0x195),
    // 購物商城信息[完成]
    CS_INFO((short) 0x196),
    CASH_SHOP((short) 0x7FFE),
    
    //================================
    // CField::OnPacket 開始(183-完成)
    //================================ 
    
    // 地圖阻擋[完成]
    MAP_BLOCKED((short) 0x197),
    // 伺服器阻擋[完成]
    SERVER_BLOCKED((short) 0x198),
    // 隊伍阻擋[完成]
    PARTY_BLOCKED((short) 0x199),
    // 裝備效果[完成]
    SHOW_EQUIP_EFFECT((short) 0x19A),
    // 组队家族聊天[别人说话可抓到]
    MULTICHAT((short) 0x19B),
    // 世界聊天模式[完成]
    WORLD_MULTICHAT((short) 0x19C),
    // 悄悄话
    WHISPER((short) 0x19D),
    // 夫妻聊天
    SPOUSE_CHAT((short) 0x19E),
    // Boss血條[完成]
    BOSS_ENV((short) 0x19F),
    // 地圖效果[完成]
    MAP_EFFECT((short) 0x1A0),
    // 祝賀音樂(5100000)[完成]
    CASH_SONG((short) 0x1A1),
    // GM效果[完成]
    GM_EFFECT((short) 0x1A2),
    // GM日誌[完成]
    GM_LOG((short) 0x1A3),    
    // 選邊站[完成]
    OX_QUIZ((short) 0x1A4),
    // GM活動說明[完成]
    GMEVENT_INSTRUCTIONS((short) 0x1A5),
    // 計時器[完成]
    CLOCK((short) 0x1A6),

    //================================
    // CField_ContiMove::OnPacket 開始(183-完成)
    //================================ 

    // 船隻移動[完成]
    BOAT_MOVE((short) 0x1A7),
    // 船隻狀態[完成]
    BOAT_STATE((short) 0x1A8),

    //================================
    // CField_ContiMove::OnPacket 結束
    //================================ 

    // 0x1A9
    // 0x1AA
    // 0x1AB
    
    // 停止計時[完成]
    STOP_CLOCK((short) 0x1AC),

    //================================
    //CField_AriantArena::OnPacket（CField_AriantArena::OnShowResult） 開始(183-完成)
    //================================ 
    // 納希競技大會分數[完成]
    ARIANT_SCOREBOARD((short) 0x1AD),
    //================================
    // CField_AriantArena::OnPacket（CField_AriantArena::OnShowResult） 結束
    //================================ 
    // 金字塔更新[完成]
    PYRAMID_UPDATE((short) 0x1AE),
    //================================
    // CField_KillCount::OnPacket 開始(183-完成)
    //================================ 
    // 金字塔擊殺數量[完成]
    PYRAMID_KILL_COUNT((short) 0x1AF),
    //================================
    // CField_KillCount::OnPacket 結束
    //================================ 
    // 金字塔分數[完成]
    PYRAMID_RESULT((short) 0x1B0),
    // 快速按鍵[完成]
    QUICK_SLOT((short) 0x1B1),
    // 移動平臺[完成]
    MOVE_PLATFORM((short) 0x1B2),
    
    // 0x1B3
    // 0x1B4 
    // 0x1B5
    // 0x1B6
    // 0x1B7
    // 0x1B8
    
    // PvP信息[完成]
    PVP_INFO((short) 0x1B9),
    // 角色站立方向狀態[完成]
    DIRECTION_STATUS((short) 0x1BA),
    // 惡魔之力[完成]
    GAIN_FORCE((short) 0x1BB),
    // 組隊任務達成率[完成]
    ACHIEVEMENT_RATIO((short) 0x1BC),
    // 快速移動[完成]
    QUICK_MOVE((short) 0x1BD),
    
    // 0x1BE
    // 0x1BF
    // 0x1C0 
    // 0x1C1
    // 0x1C2 // 副本BOSS技能特效
    // 0x1C3
    // 0x1C4 // 副本BOSS技能特效
    // 0x1C5
    // 0x1C6
    // 0x1C7
    // 0x1C8
    // 0x1C9
    // 0x1CA // 帳號保護
    // 0x1CB
    // 0x1CC
    // 0x1CD
    // 0x1CE
    // 0x1CF // 完成造型王預賽參加申請
    // 0x1D0 // 菁英怪物
    // 0x1D1
    // 0x1D2
    // 0x1D3
    // 0x1D4
    // 0x1D5
    // 0x1D6
    // 0x1D7
    // 0x1D8
    // 0x1D9
    // 0x1DA
    // 0x1DB
    // 0x1DC
    
    //================================
    // CUserPool::OnPacket 開始(183-完成)
    //================================ 

    // 召喚玩家[完成]
    SPAWN_PLAYER((short) 0x1DD),
    // 移除玩家[完成]
    REMOVE_PLAYER_FROM_MAP((short) 0x1DE),
    
    //================================
    // CUserPool::OnUserCommonPacket 開始(183-完成)
    //================================ 
    
    // 普通聊天[完成]
    CHATTEXT((short) 0x1DF),
    // 黑板[完成]
    CHALKBOARD((short) 0x1E0),
    // 更新玩家[完成]
    UPDATE_CHAR_BOX((short) 0x1E1),
    // 消費效果[未知]
    SHOW_CONSUME_EFFECT((short) 0x1E2),
    // 使用卷軸效果[完成]
    SHOW_SCROLL_EFFECT((short) 0x1E3),
    
    // 0x1E4 (null)
    
    // 咒文的痕跡[完成]
    SHOW_ENCHANTER_EFFECT((short) 0x1E5), 
    // 使用魂之珠[完成]
    SHOW_SOULSCROLL_EFFECT((short) 0x1E6), 
    // 放大鏡效果[完成]
    SHOW_MAGNIFYING_EFFECT((short) 0x1E7),
    // 擴充潛能欄位
    SHOW_POTENTIAL_EXPANSION((short) 0x1E8),
    // 潛能重置效果
    SHOW_POTENTIAL_RESET((short) 0x1E9),
    // 重新設置潛能效果
    SHOW_BONUS_POTENTIAL_RESET((short) 0x1EA),
    
    // 0x1EB 使用幸運卷?
    
    // 顯示煙花效果
    SHOW_FIREWORKS_EFFECT((short) 0x1EC),
    
    // 0x1ED
    // 0x1EE
    // 0x1EF
    
    // 顯示星岩效果
    SHOW_NEBULITE_EFFECT((short) 0x1F0),
    // 顯示合成效果
    SHOW_FUSION_EFFECT((short) 0x1F1),
    // PvP攻擊
    PVP_ATTACK((short) 0x1F2),
    // PvP煙霧[完成] find:[ invenom ]
    PVP_MIST((short) 0x1F3),
    
    // 0x1F4
    
    //PvP冷卻時間
    PVP_COOL((short) 0x1F5),
    //磁場技能
    TESLA_TRIANGLE((short) 0x1F6),
    
    //0x1F7
    
    //跟随状态
    FOLLOW_EFFECT((short) 0x1F8),
    //顯示組隊任務獎勵[完成]
    SHOW_PQ_REWARD((short) 0x1F9),
    //工藝效果
    CRAFT_EFFECT((short) 0x1FA),
    //工藝完成
    CRAFT_COMPLETE((short) 0x1FB),
    //採集結束特效
    HARVESTED_EFFECT((short) 0x1FC),
    //採集結束
    HARVESTED((short) 0x1FD),
    
    // 0x1FE
    
    //玩家傷害
    PLAYER_DAMAGED((short) 0x1FF),
    //奈特的金字塔
    NETT_PYRAMID((short) 0x200),
    //設定特效
    SET_PHASE((short) 0x201),
    
    // 0x202
    // 0x203
    // 0x204
    // 0x205
    // 0x206

    //潘姆音樂
    PAMS_SONG((short) 0x207),
    //取消椅子
    CANCEL_CHAIR((short) 0x208),
    
    // 0x209
    // 0x20A
    // 0x20B
    
    //攻擊Skin[完成]
    SHOW_DAMAGE_SKIN((short) 0x20C),
    
    // 0x20D
    // 0x20E
    // 0x20F
    // 0x210
    // 裝備特效開關
    EFFECT_SWITCH((short) 0x211),
    // 0x212
    // 0x213
    // 0x214
    // 0x215
    // 0x216
    // 0x217
    // 0x218
    // 0x219
    // 0x21A
    // 0x21B
    // 0x21C
    
    //================================
    // CUserPool::OnUserPetPacket 開始(183-完成)
    //================================ 
    
    //召喚寵物[完成]
    SPAWN_PET((short) 0x21D),
    //寵物移動[完成]
    MOVE_PET((short) 0x21E),
    //寵物說話[完成]
    PET_CHAT((short) 0x21F),
    //變更寵物名稱
    PET_NAMECHANGE((short) 0x220),
    
    // 0x221
    
    //寵物例外清單[完成]
    PET_EXCEPTION_LIST((short) 0x222),
    //寵物顏色[完成]
    PET_COLOR((short) 0x223),
    //寵物大小[完成]
    PET_SIZE((short) 0x224),
    
    // 0x225
    
    //顯示寵物[完成]
    SHOW_PET((short) 0x226),
    //寵物指令[完成]
    PET_COMMAND((short) 0x227),
    
    //================================
    // CUserPool::OnUserDragonPacket 開始(183-完成)
    //================================ 
    
    //召喚龍神[完成] 
    DRAGON_SPAWN((short) 0x228),
    //龍神移動[完成]
    DRAGON_MOVE((short) 0x229),
    //移除龍神[完成]
    DRAGON_REMOVE((short) 0x22A),
    
    //================================
    // CUserPool::OnUserAndroidPacket 開始(183-完成)
    //================================ 
    
    //召喚機器人[完成]
    ANDROID_SPAWN((short) 0x22B),
    //機器人移動[完成]
    ANDROID_MOVE((short) 0x22C),
    //機器人情緒[完成]
    ANDROID_EMOTION((short) 0x22D),
    //更新機器人外觀[完成]
    ANDROID_UPDATE((short) 0x22E),
    //移除機器人[完成]
    ANDROID_DEACTIVATED((short) 0x22F),
    
    //================================
    // CUserPool::OnUserHakuPacket1 開始(183-完成)
    //================================ 
    
    //變更花弧
    HAKU_CHANGE_1((short) 0x230),
    
    // 0x231
    
    //花狐使用技能後發的(Done)
    HAKU_USE_BUFF((short) 0x232),
    //變更花弧
    HAKU_CHANGE_0((short) 0x233),
    
    // 0x234
    
    //花弧未知
    HAKU_UNK((short) 0x235),
    
    //================================
    // CUserPool::OnUserHakuPacket2 開始(183-完成)
    //================================ 
    
    // 0x236
    
    //花狐移動
    HAKU_MOVE((short) 0x237),
    //花狐更新
    HAKU_UPDATE((short) 0x238),
    //變更花狐
    HAKU_CHANGE((short) 0x239),
    
    // 0x23A[NULL]
    // 0x23B[NULL]
    
    //召喚花狐[完成]
    SPAWN_HAKU((short) 0x23C),
    
    // 0x23D
    // 0x23E
    
    //================================
    // CUserPool::OnUserRemotePacket 開始(183-完成)
    //================================ 
    
    // 玩家移動[完成]
    MOVE_PLAYER((short) 0x23F),    
    // 近距離攻擊[完成]
    CLOSE_RANGE_ATTACK((short) 0x240),
    // 遠距離攻擊[完成]
    RANGED_ATTACK((short) 0x241),
    // 魔法攻擊[完成]
    MAGIC_ATTACK((short) 0x242),
    // 被動攻擊[完成]
    PASSIVE_ATTACK((short) 0x243),
    //技能效果[完成][用主教的创世之破抓到包]
    SKILL_EFFECT((short) 0x244),
    //移動攻擊[完成][33121214 - 狂野機關砲]
    MOVE_ATTACK((short) 0x245),
    //取消技能效果[完成]
    CANCEL_SKILL_EFFECT((short) 0x246),
    //玩家受到傷害[完成]
    DAMAGE_PLAYER((short) 0x247),
    //玩家面部表情[完成]
    FACIAL_EXPRESSION((short) 0x248),
    
    // 0x249
    
    //顯示物品效果
    SHOW_EFFECT((short) 0x24A),
    //顯示頭上稱號
    SHOW_TITLE((short) 0x24B),
    //天使破壞者變更
    ANGELIC_CHANGE((short) 0x24C),
    
    // 0x24D
    // 0x24E
    // 0x24F
    
    //顯示椅子效果[完成]
    SHOW_CHAIR((short) 0x250),
    //更新玩家外觀[完成]
    UPDATE_CHAR_LOOK((short) 0x251),
    //玩家外觀效果[完成]
    SHOW_FOREIGN_EFFECT((short) 0x252),
    //獲得異常狀態[完成]
    GIVE_FOREIGN_BUFF((short) 0x253),
    //取消異常狀態
    CANCEL_FOREIGN_BUFF((short) 0x254),
    //更新隊員血量
    UPDATE_PARTYMEMBER_HP((short) 0x255),
    //讀取公會名稱[完成]
    LOAD_GUILD_NAME((short) 0x256),
    //讀取公會標誌[完成]
    LOAD_GUILD_ICON((short) 0x257),
    //讀取隊伍(Done)
    LOAD_TEAM((short) 0x258),
    //採集
    SHOW_HARVEST((short) 0x259),
    //PvP血量
    PVP_HP((short) 0x25A),
    
    // 0x25B
    // 0x25C
    // 0x25D
    // 0x25E
    // 0x25F
    // 0x260
    // 0x261
    // 0x262
    // 0x263
    // 0x264
    
    //神之子狀態
    ZERO_MUITTAG((short) 0x265),
    
    // 0x266
    // 0x267
    // 0x268
    // 0x269 [NULL]
    // 0x26A
    // 0x26B
    // 0x26C
    // 0x26D
    
    //================================
    // CUserPool::OnUserLocalPacket 開始(183-完成)
    //================================ 
    
    // 動畫表情[完成]
    DIRECTION_FACIAL_EXPRESSION((short) 0x26F),
    // 畫面移動
    MOVE_SCREEN((short) 0x270),
    // 顯示物品效果[完成]
    SHOW_SPECIAL_EFFECT((short) 0x271),
    // 角色地圖瞬移 武陵道場會出現
    CURRENT_MAP_WARP((short) 0x272),
    
    // 0x273
    
    // 使用福包成功(5200000)[完成]
    MESOBAG_SUCCESS((short) 0x274),
    // 使用福包失敗(5200000)[完成]
    MESOBAG_FAILURE((short) 0x275),
    // 更新任務信息
    UPDATE_QUEST_INFO((short) 0x276),
    // 血量減少
    HP_DECREASE((short) 0x277),
    // 變更寵物技能[完成]
    PET_FLAG_CHANGE((short) 0x278),
    // 玩家提示[完成]
    PLAYER_HINT((short) 0x279),
    // 播放事件音效
    PLAY_EVENT_SOUND((short) 0x27A),
    // 播放迷你遊戲音效
    PLAY_MINIGAME_SOUND((short) 0x27B),
    // 生產用技能
    MAKER_SKILL((short) 0x27C),
    
    // 0x27D (Null)
    // 0x27E
    
    // 開啟介面[完成]
    OPEN_UI((short) 0x27F),
    
    // 0x280
    
    // 開啟選項介面[完成]
    OPEN_UI_OPTION((short) 0x281),
    
    // 0x282
    
    // 鎖定玩家按鍵動作[完成]
    LOCK_KEY((short) 0x283),
    // 劇情鎖定介面[完成]
    LOCK_UI((short) 0x284),
    // 不顯示其他玩家[完成]
    DISABLE_OTHERS((short) 0x285),
    // 召喚初心者幫手[完成]
    SUMMON_HINT((short) 0x286),
    // 初心者幫手訊息[完成]
    SUMMON_HINT_MSG((short) 0x287),
    
    // 0x288
    // 0x289
    // 0x28A
    
    // 狂狼勇士連擊[完成]
    ARAN_COMBO((short) 0x28B),
    // 狂狼勇士鬥氣重生[完成]
    ARAN_COMBO_RECHARGE((short) 0x28C),
    
    // 0x28D
    // 0x28E
    
    //公告提示[完成]
    GAME_MSG((short) 0x28F),
    //遊戲訊息[完成]
    GAME_MESSAGE((short) 0x290),
    
    // 0x291 [String][Int]
    
    //
    BUFF_ZONE_EFFECT((short) 0x292),
    //
    DAMAGE_METER((short) 0x293),
    //炸彈攻擊
    TIME_BOMB_ATTACK((short) 0x294),
    //跟随移动
    FOLLOW_MOVE((short) 0x295),
    //跟随信息
    FOLLOW_MSG((short) 0x296),
    
    //　0x297
    
    //建立終極冒險家
    CREATE_ULTIMATE((short) 0x298),
    //採集訊息
    HARVEST_MESSAGE((short) 0x299),
    //符文介面
    RUNE_ACTION((short) 0x29A),
    //礦物背包
    OPEN_BAG((short) 0x29B),
    //龍之氣息
    DRAGON_BLINK((short) 0x29C),
    //PvP冰騎士
    PVP_ICEGAGE((short) 0x29D),
    //位置信息[完成]
    DIRECTION_INFO((short) 0x29E),
    //重新獲得勳章
    REISSUE_MEDAL((short) 0x29F),
    
    // 0x2A0
    // 0x2A1 [Int]
    
    //动画播放[完成]
    PLAY_MOVIE((short) 0x2A2),
    //蛋糕 vs 派餅 活動
    CAKE_VS_PIE_MSG((short) 0x2A3),
    //幻影俠盜卡片[完成]
    PHANTOM_CARD((short) 0x2A4),
    
    // 0x2A5 [Int]
    // 0x2A6 [Int][Byte]
    
    //夜光連擊
    LUMINOUS_COMBO((short) 0x2A7),
    
    // 0x2A8
    // 0x2A9
    // 0x2AA
    // 0x2AB
    // 0x2AC
    // 0x2AD
    // 0x2AE
    // 0x2AF
    // 0x2B0
    // 0x2B1
    // 0x2B2
    // 0x2B3
    // 0x2B4
    // 0x2B5
    //死亡視窗
    DEATH_TIP((short) 0x2B6),
    // 0x2B7
    // 0x2B8
    // 0x2B9
    
    //時間膠囊[完成] (3010587)
    TIME_CAPSULE((short) 0x2BA),
    
    // 0x2BB
    // 0x2BC
    // 0x2BD
    // 0x2BE
    
    //神之子衝擊波[完成] (101000102)
    ZERO_SHOCKWAVE((short) 0x2BF),
    //設定槍的名稱[完成]
    SET_GUN_NAME((short) 0x2C0),
    //設定槍彈[完成]
    SET_GUN_AMMO((short) 0x2C1),
    //建立槍[完成]
    CREATE_GUN((short) 0x2C2),
    //清除槍[完成]
    CLEAR_GUN((short) 0x2C3),
    
    // 0x2C4
    // 0x2C5
    // 0x2C6
    // 0x2C7
    // 0x2C8
    // 0x2C9
    
    //戰鬥回復[完成] (101110205)
    ZERO_BATTLE_HEAL((short) 0x2CA),
    
    // 0x2CB
    // 0x2CC
    // 0x2CD
    // 0x2CE
    // 0x2CF
    // 0x2D0
    // 0x2D1
    // 0x2D2
    // 0x2D3
    // 0x2D4
        
    //神之子參數(Done)
    ZERO_OPTION((short) 0x2D5),
    //翻轉硬幣(Done)
    FLIP_THE_COIN((short) 0x2D6),
    
    // 0x2D7
    // 0x2D8
    // 0x2D9
    // 0x2DA
    
    //幽靈水彩特效(Done) (skill == 80001408)
    GHOST_WATERCOLOR_EFFECT((short) 0x2DB),
    //符文特效(Done) (80001429)
    RUNE_EFFECT((short) 0x2DC),
    
    // 0x2DD
    // 0x2DE
    // 0x2DF
    // 0x2E0
    // 0x2E1
    // 0x2E2
    // 0x2E3
    // 0x2E4
    // 0x2E5
    // 0x2E6
    // 0x2E7
    // 0x2E8
    // 0x2E9
    
    // Setp GiftID[完成]
    SETP_GIFT_ID((short) 0x2EA),
    
    // 0x2EB
    
    // Step Coin[完成]
    SETP_COIN((short) 0x2EC),
    //凱撒快速鍵[完成]
    KAISER_QUICK_KEY((short) 0x2ED),
    
    // 0x2EE
    // 0x2EF
    // 0x2F0
    // 0x2F1
    // 0x2F2
    // 0x2F3
    // 0x2F4
    // 0x2F5
    // 0x2F6
    // 0x2F7
    // 0x2F8
    // 0x2F9
    // 0x2FA
    // 0x2FB
    // 0x2FC
    // 0x2FD
    // 0x2FE
    // 0x2FF
    // 0x300
    // 0x301
    // 0x302
    // 0x303
    // 0x304
    // 0x305
    // 0x306
    // 0x307
    // 0x308
    // 0x309
    // 0x30A
    // 0x30B
    // 0x30C
    
    //閃耀方塊反饋 (CField頂端內容有/12的用xRef回找)
    FLASH_CUBE_RESPONSE((short) 0x30D),
    
    // 0x30F
    // 0x310
    // 0x311
    // 0x312
    // 0x313
    // 0x314
    // 0x315
    // 0x316
    // 0x317
    // 0x318
    // 0x319
    // 0x31A
    // 0x31B
    // 0x31C
    // 0x31D
    // 0x31E
    // 0x31F
    // 0x320
    
    // 0x321 跟技能冷卻很像的Sub
    
    // 0x322
    // 0x323
    // 0x324
    // 0x325
    // 0x326
    // 0x327
    // 0x328
    
    //技能冷却[完成]
    COOLDOWN((short) 0x32A),
    
    // 0x32B

    //================================
    // CUser::OnSummonedPacket 開始(184-完成)
    //================================ 
    
    //招喚招喚獸[完成]
    SPAWN_SUMMON((short) 0x32C),
    //移除招喚獸[完成]
    REMOVE_SUMMON((short) 0x32D),
    //招喚獸移動[完成]
    MOVE_SUMMON((short) 0x32E),
    //招喚獸攻击[完成]
    SUMMON_ATTACK((short) 0x32F),
    //PvP招喚獸
    PVP_SUMMON((short) 0x330),
    //招喚獸技能
    SUMMON_SKILL_2((short) 0x331),
    //招喚獸技能
    SUMMON_SKILL((short) 0x332),
    //招喚獸延遲
    SUMMON_DELAY((short) 0x333),
    //招喚獸受傷
    DAMAGE_SUMMON((short) 0x334),
    
    // 0x335
    // 0x336
    // 0x337
    // 0x338
    // 0x339
    // 0x33A
    
    //================================
    // CMobPool::OnMobPacket 開始(184-完成)
    //================================ 
    
    // 怪物召喚[完成]
    SPAWN_MONSTER((short) 0x33B),
    // 殺除怪物[完成]
    KILL_MONSTER((short) 0x33C),
    // 控制召喚怪物[完成]
    SPAWN_MONSTER_CONTROL((short) 0x33D),
    
    // 0x33E [Int(MOB_ID)][Short][Int][Byte]
    
    // 怪物移動[完成]
    MOVE_MONSTER((short) 0x33F),
    // 怪物移動回覆[完成]
    MOVE_MONSTER_RESPONSE((short) 0x340),
    
    // 0x341 (NULL)
    
    //添加怪物状态[完成]
    APPLY_MONSTER_STATUS((short) 0x342),
    //取消怪物状态[完成]
    CANCEL_MONSTER_STATUS((short) 0x343),    
    //怪物暫停重置[完成]
    MONSTER_SUSPEND_RESET((short) 0x344),
    //影響怪物[完成]
    MONSTER_AFFECTED((short) 0x345),
    //怪物受到伤害
    DAMAGE_MONSTER((short) 0x346),
    //怪物技能特效[完成]
    SKILL_EFFECT_MOB((short) 0x347),
    
    // 0x348 (NULL)
    
    //怪物CRC[完成] 接收=> 0x156
    MONSTER_CRC_CHANGE((short) 0x349),
    //顯示怪物HP[完成]
    SHOW_MONSTER_HP((short) 0x34A),
    //捕抓怪物[完成]
    CATCH_MONSTER((short) 0x34B),
    //怪物物品特效[完成]
    ITEM_EFFECT_MOB((short) 0x34C),
    
    // 0x34D
    
    //怪物說話[完成]
    TALK_MONSTER((short) 0x34E),
    //移除怪物說話
    REMOVE_TALK_MONSTER((short) 0x34F),
    //怪物技能延遲[完成]
    MONSTER_SKILL_DELAY((short) 0x350),
    //怪物護送全部路徑[完成]
    MONSTER_ESCORT_FULL_PATH((short) 0x351),
    //怪物護送暫停/停止允許[完成]
    MONSTER_ESCORT_STOP_END_PERMISSION((short) 0x352),
    //怪物護送暫停/停止允許[完成]
    MONSTER_ESCORT_STOP_END_PERMISSION2((short) 0x353),
    //怪物護送暫停說話[完成]
    MONSTER_ESCORT_STOP_SAY((short) 0x354),
    //怪物護送返回前[完成]
    MONSTER_ESCORT_RETURN_BEFORE((short) 0x355),
    //怪物下個攻擊[完成]
    MONSTER_NEXT_ATTACK((short) 0x356),
    
    // 0x357
    // 0x358
    // 0x359
    // 0x35A
    // 0x35B
    // 0x35C
    // 0x35D
    // 0x35E
    // 0x35F
    // 0x360
    // 0x361
    // 0x362
    // 0x363
    // 0x364
    // 0x365
    // 0x366
    // 0x367
    // 0x368
    // 0x369
    // 0x36A
    // 0x36B
    // 0x36C
    // 0x36D
    // 0x36E
    // 0x36F
    
    //怪物攻擊怪物[完成]
    MOB_TO_MOB_DAMAGE((short) 0x370),
    
    // 0x371 (NULL)    
    
    //================================
    // CMobPool::OnAzwanMobPacket 開始(184-完成)
    //================================ 
    
    // 阿斯旺怪物攻擊怪物[完成]
    AZWAN_MOB_TO_MOB_DAMAGE((short) 0x372),
    // 阿斯旺怪物召喚[完成]
    AZWAN_SPAWN_MONSTER((short) 0x373),
    // 阿斯旺怪物死亡[完成]
    AZWAN_KILL_MONSTER((short) 0x374),
    // 阿斯旺控制召喚怪物[完成]
    AZWAN_SPAWN_MONSTER_CONTROL((short) 0x375),
    
    //================================
    // CNpcPool::OnPacket 開始(184-完成)
    //================================ 
    
    // 召喚Npc[完成]
    SPAWN_NPC((short) 0x376),
    // 移除Npc[完成]
    REMOVE_NPC((short) 0x377),
    
    // 0x378
    
    // 控制召喚Npc[完成]
    SPAWN_NPC_REQUEST_CONTROLLER((short) 0x379),
    // Npc動作[完成]
    NPC_ACTION((short) 0x37A),
    
    // 0x37B
    // 0x37C
    // 0x37D
    
    // 更新NPC狀態信息
    NPC_UPDATE_LIMITED_INFO((short) 0x37E),
    
    // 0x37F
    // 0x380
    // 0x381
    // 0x382
    // 0x383
    // 0x384
    // 重置NPC動作
    RESET_NPC((short) 0x385),
    // 0x386
    // 0x387
    // 0x388
    // 0x389
    
    // Npc特殊事件[完成]
    NPC_SET_SPECIAL_ACTION((short) 0x38A),
    // 設置Npc腳本[完成]
    NPC_SCRIPTABLE((short) 0x38B),
    
    // 0x38C
    
    //================================
    // CEmployeePool::OnPacket 開始(184-完成)
    //================================ 
    
    // 召喚精靈商人
    SPAWN_HIRED_MERCHANT((short) 0x38D),
    // 移除精靈商人
    DESTROY_HIRED_MERCHANT((short) 0x38E),
    
    // 0x38F
    
    // 精靈商人更新
    UPDATE_HIRED_MERCHANT((short) 0x390),
    
    //================================
    // CDropPool::OnPacket 開始(184-完成)
    //================================ 
    
    // 物品掉落[完成]
    DROP_ITEM_FROM_MAPOBJECT((short) 0x391),
    
    // 0x392 (null)
    
    // 物品消失[完成]
    REMOVE_ITEM_FROM_MAP((short) 0x393),
    
    //================================
    // CMessageBoxPool::OnPacket 開始(184-完成)
    //================================ 
    
    // 召喚風箏錯誤[完成]
    SPAWN_KITE_ERROR((short) 0x394),
    // 召喚風箏[完成]
    SPAWN_KITE((short) 0x395),
    // 移除風箏[完成]
    DESTROY_KITE((short) 0x396),
    
    //================================
    // CAffectedAreaPool::OnPacket 開始(184-完成)
    //================================ 
    
    // 召喚煙霧[完成]
    SPAWN_MIST((short) 0x397),
    // 煙霧未知[完成]
    MIST_UNK((short) 0x398),
    // 移除煙霧[完成]
    REMOVE_MIST((short) 0x399),
    
    //================================
    // CTownPortalPool::OnPacket 開始(184-完成)
    //================================ 
    
    // 時空門[完成]
    SPAWN_DOOR((short) 0x39A),
    // 移除時空門[完成]
    REMOVE_DOOR((short) 0x39B),
    
    //================================
    // C182AddUnkPool::OnPacket 開始(184-完成)
    //================================ 
    
    // 0x39C
    // 0x39D
    // 0x39E
    
    //================================
    // COpenGatePool::OnPacket 開始(184-完成)
    //================================ 
    
    // 召喚開放通道[完成]
    MECH_DOOR_SPAWN((short) 0x39F),
    // 移除開放通道[完成]
    MECH_DOOR_REMOVE((short) 0x3A0),
    
    //================================
    // CReactorPool::OnPacket 開始(184-完成)
    //================================ 
    
    // 攻擊箱子[完成]
    REACTOR_HIT((short) 0x3A1),
    // 箱子移動[完成]
    REACTOR_MOVE((short) 0x3A2),
    // 召喚箱子[完成]
    REACTOR_SPAWN((short) 0x3A3),
    // 箱子未知[完成]
    REACTOR_UNK((short) 0x3A4),
    // 箱子未知2[完成]
    REACTOR_UNK2((short) 0x3A5),
    // 箱子消失(隱藏?)[Int - oid][完成]
    REACTOR_UNK3((short) 0x3A6),
    // 重置箱子[完成]
    REACTOR_DESTROY((short) 0x3A7),
    
    //================================
    // CReactorPool::OnExtractorPacket 開始(184-完成)
    //================================ 
    
    // 召喚分解機[完成]
    SPAWN_EXTRACTOR((short) 0x3A8),
    // 移除分解機[完成]
    REMOVE_EXTRACTOR((short) 0x3A9),
    
    //================================
    // CEventsPool::OnPacket 開始(184-完成)「CField_SnowBall::OnPacket」
    //================================ 
    
    //滾動雪球
    ROLL_SNOWBALL((short) 0x3AA),
    //攻擊雪球
    HIT_SNOWBALL((short) 0x3AB),
    //雪球訊息
    SNOWBALL_MESSAGE((short) 0x3AC),
    //向左擊飛
    LEFT_KNOCK_BACK((short) 0x3AD),

    //================================
    // CField_Coconut::OnPacket 開始(184-完成)
    //================================ 

    //攻擊椰子
    HIT_COCONUT((short) 0x3AE),
    //椰子活動分數
    COCONUT_SCORE((short) 0x3AF),

    //================================
    // CField_GuildBoss::OnPacket 開始(184-完成)
    //================================ 

    // CField_GuildBoss::OnHealerMove[完成]
    MOVE_HEALER((short) 0x3B0),
    // CField_GuildBoss::OnPulleyStateChange[完成]
    PULLEY_STATE((short) 0x3B1),

    //================================
    // CField_MonsterCarnival::OnPacket 開始(184-完成)
    //================================ 
    // 怪物擂台賽開始[完成]
    MONSTER_CARNIVAL_START((short) 0x3B2),
    // 怪物擂台賽獲得CP[完成]
    MONSTER_CARNIVAL_OBTAINED_CP((short) 0x3B3),
    // 怪物擂台賽狀態[完成]
    MONSTER_CARNIVAL_STATS((short) 0x3B4),
    
    // 0x3B5 [Int] * 4
    
    // 怪物擂台賽召喚[完成]
    MONSTER_CARNIVAL_SUMMON((short) 0x3B6),
    // 怪物擂台賽訊息[完成]
    MONSTER_CARNIVAL_MESSAGE((short) 0x3B7),
    // 怪物擂台賽死亡[完成]
    MONSTER_CARNIVAL_DIED((short) 0x3B8),
    // 怪物擂台賽離開[完成]
    MONSTER_CARNIVAL_LEAVE((short) 0x3B9),
    // 怪物擂台賽分數[完成]
    MONSTER_CARNIVAL_RESULT((short) 0x3BA),
    // 怪物擂台賽排名[完成]
    MONSTER_CARNIVAL_RANKING((short) 0x3BB),

    //================================
    // CField_AriantArena::OnPacket 開始(184-完成)
    //================================ 

    // 更新納希競技大會分數[完成]
    ARIANT_SCORE_UPDATE((short) 0x3BC),

    //================================
    // CField_AriantArena::OnPacket 結束
    //================================ 

    // 0x3BD
    // 開心牧場資訊
    SHEEP_RANCH_INFO((short) 0x3BE),
    // 開心牧場衣服
    SHEEP_RANCH_CLOTHES((short) 0x3BF),
    // 魔女之塔
    WITCH_TOWER((short) 0x3C0),
    // 遠征隊挑戰[完成]
    EXPEDITION_CHALLENGE((short) 0x3C1),
    // 炎魔祭壇[完成]
    ZAKUM_SHRINE((short) 0x3C2),
    // PvP類型[完成]
    PVP_TYPE((short) 0x3C3),
    // PvP轉移[完成]
    PVP_TRANSFORM((short) 0x3C4),
    // PvP[完成]
    PVP_DETAILS((short) 0x3C5),
    // PvP開始[完成]
    PVP_ENABLED((short) 0x3C6),
    // PvP分數[完成]
    PVP_SCORE((short) 0x3C7),
    // PvP結果[完成]
    PVP_RESULT((short) 0x3C8),
    // PvP隊伍[完成]
    PVP_TEAM((short) 0x3C9),
    // PvP計分板[完成]
    PVP_SCOREBOARD((short) 0x3CA),
    
    // 0x3CB [Int][Byte]
    
    // PvP點數[完成]
    PVP_POINTS((short) 0x3CC),
    // PvP擊殺數[完成]
    PVP_KILLED((short) 0x3CD),
    // PvP模式[完成]
    PVP_MODE((short) 0x3CE),
    // PvP冰騎士[完成]
    PVP_ICEKNIGHT((short) 0x3CF),
    
    // sub_688DEC {
    // 0x3D0
    // 0x3D1
    // 0x3D2
    // 0x3D3(NULL)
    // 0x3D4
    // }
    
    // sub_6BAE8E {
    // 0x3D5
    // 0x3D8
    // }
    
    // sub_6C093D {
    // 0x3D6
    // 0x3D7
    // 0x3D9
    // }
    
    // sub_6B8599 {
    // 0x3DA
    // 0x3DB
    // 0x3DC
    // 0x3DD
    // 0x3DE
    // 0x3DF
    // 0x3E0
    // 0x3E1
    // 0x3E2
    // }
    
    // sub_6A7E5B {
    // 0x3E3
    // 0x3E4
    // }

    // 0x3E5

    // sub_6C8B36 { 【遊戲(未確認)】
    // 0x3E6
    // 0x3E7
    // 0x3E8
    // 0x3E9
    // 0x3EA
    // 0x3EB
    // 0x3EC
    // 0x3ED
    // 0x3EE 【楓葉戰士】
    // 0x3EF
    // 0x3F0
    // 0x3F1
    // 0x3F5
    // }
    
    // sub_6BDC3E {
    // 0x3F2
    // 0x3F3
    // 0x3F4
    // }

    // sub_63E919 {
    // 0x3F6【排行介面】
    // }

    // 0x3F7
    // 0x3F8

    // sub_6AF283 {
    // 0x3F9
    // 0x3FA
    //}

    //0x3FB
    //0x3FC

    // sub_68DCA9 {
    // 0x3FD
    // 0x3FE
    // 0x3FF
    // 0x400
    // }
    
    // sub_679B8A { 【楓葉戰士(未確認)】
    // 0x401 【楓葉戰士】【Effect/BasicEff.img/rhythmGame/fever%d】 [Int][Int]
    // 0x402
    // 0x403
    // 0x404
    // 0x405
    // }

    // sub_697DDF {
    // 0x40B
    // 0x40C
    // 0x40D
    // 0x40E
    // 0x411
    // }
    
    // sub_67E742 { 【BossPvP】
    // 0x406 [Int]
    // 0x407 [Int][Int][Int]
    // 0x408 [Int][Int][Int]
    // 0x409 【UI/UIWindow4.img/bossArena/selectUi/boss/%d】
    // 0x40A 【UI/UIWindow4.img/bossArena/selectUi/ready】
    // 0x40F
    // 0x410 [Int]
    // 0x412
    // 0x413 【座椅效果?】
    // 0x414
    // 0x415
    // }
    
    // sub_6D6C10 {
    // 0x416
    // 0x417
    // 0x418
    // 0x419
    // 0x41A
    // 0x41B
    // 0x3DD
    // }
    
    // 召喚符文[完成-184]
    SPAWN_RUNE((short) 0x423),
    // 移除符文[完成-184]
    REMOVE_RUNE((short) 0x424),
    // 重新召喚符文[完成-184]
    RESPAWN_RUNE((short) 0x425),
    
    // sub_63AB26 {
    // 0x3E4 【UI/StarCityUI.img/Screen/WorldEvent/%d】
    // }
    
    // sub_6E6480 {
    // 0x3EF
    // 0x3F0
    // 0x3F1
    // 0x3F2
    // 0x3F3
    // 0x3F4
    // 0x3F5 【UI/UIWindow4.img/typingDefense/Result/gameover】【UI/UIWindow4.img/typingDefense/Result/clear】
    // 0x3F6
    // 0x3F7
    // 0x3F8
    // 0x3F9
    // 0x400
    // }
    
    // sub_69A320 { 【迷你遊戲】
    // 0x3E5
    // 0x3E6
    // 0x3E7 【Sound/MiniGame.img/】
    // 0x3E8
    // 0x3E9
    // 0x3EA
    // 0x3F1
    // 0x3F2
    // }
    
    // sub_69D380 {
    // 0x3FB
    // 0x3FC
    // 0x3FD
    // 0x3FE
    // 0x3FF
    // 0x400
    // 0x401
    // }
    
    // sub_6CBFA7 {
    // 0x402
    // }
    
    // 混沌炎魔祭壇[完成]
    CHAOS_ZAKUM_SHRINE((short) 0x403),
    
    // 0x404
    
    // 闇黑龍王祭壇[完成]【未確認】
    HORNTAIL_SHRINE((short) 0x405),
    
    // 購物商城更新楓幣
    CS_MESO_UPDATE((short) 0x40F),
    // 商城搭配
    CS_COLLOCATTON((short) 0x412),
    
    // sub_67C061 { 【怪物擂台賽】
    // 0x409
    // 0x40A
    // 0x40B [-]
    // 0x40C [-]
    // 0x40D
    // 0x40E
    // 0x40F
    // 0x410
    // 0x411
    // 0x412
    // 0x413
    // }
    
    // sub_680FAC { 【蛋糕 Vs 派餅 活動】
    // 0x416
    // 0x417 【%s 陣營的Boss已經被召喚。】
    // 0x418 【更新Boss血條】
    // 0x419 【攻擊效果】[Byte(00:Miss、01:Cool)]
    // 0x41D
    // 0x420
    // }
    
    // sub_67FEDA {
    // 0x41A [Byte][Int][Byte]
    // }
    
    // sub_67F7EB {
    // 0x41B
    // 0x41C
    // }
    
    // sub_67F8C5 {
    // 0x41E [Byte]
    // }
    
    // sub_6DAE51 {
    // 0x423
    // 0x424
    // 0x425
    // 0x426
    // 0x427
    // 0x428
    // 0x429
    // }
    
    // sub_6CF7E2 {+0x18 178ok
    // 0x42A
    // 0x42B
    // 0x42C
    // 0x42D
    // 0x42E
    // 0x42F
    // 0x430
    // }
    
    // PvP(奪旗模式)
    CAPTURE_FLAGS((short) 0x459),
    CAPTURE_POSITION((short) 0x45A),
    CAPTURE_RESET((short) 0x45B),
    // 粉紅色炎魔祭壇[完成]
    PINK_ZAKUM_SHRINE((short) 0x45C),
    
    //================================
    // CScriptMan::OnPacket 開始(184-完成)
    //================================ 
    
    // Npc交談[完成]
    NPC_TALK((short) 0x4AF),
    
    //================================
    // CShopDlg::OnPacket 開始(184-完成)
    //================================ 
    
    // Npc商店[完成]
    OPEN_NPC_SHOP((short) 0x4B0),
    // 購買Npc商店道具[完成]
    CONFIRM_SHOP_TRANSACTION((short) 0x4B1),
    
    //================================
    // CAdminShopDlg::OnPacket 開始(184-完成)
    //================================ 
    
    // 管理員商店[完成]
    ADMIN_SHOP_RESULT((short) 0x4B2),
    // 管理員商店-商品[完成]
    ADMIN_SHOP_COMMODITY((short) 0x4B3),
    
    //================================
    // CAdminShopDlg::OnPacket 結束
    //================================ 
    
    // 0x4B4
    // 0x4B5
    // 0x4B6
    // 0x4B7
    // 0x4B8
    // 0x4B9
    // 0x4BA
    
    //================================
    // sub_6F1C61 開始(184-完成)
    //================================ 
    
    // 0x4BB
    // 0x4BC
    // 0x4BD 【靈魂陷阱】
    // 0x4BE
    // 0x4BF
    // 0x4C0
    // 0x4C1
    // 0x4C2 【姆勒姆勒地城地圖】、【姆勒姆勒的炸彈】
    // 0x4C3
    
    //================================
    // sub_6F1C61 結束
    //================================ 
    
    // 0x4C4
    // 0x4C5
    
    //================================
    // sub_6A7380 開始(184-完成)
    //================================ 
    
    
    // 0x4C6
    
    //================================
    // sub_6A7380 結束
    //================================ 
    
    // 0x4C7
    
    // 倉庫[完成]
    OPEN_STORAGE((short) 0x4C8),
    
    //================================
    // CStoreBankDlg::OnPacket 開始(184-完成)
    //================================
    
    // 富蘭德里訊息
    MERCH_ITEM_MSG((short) 0x4C9),
    // 富蘭德里倉庫
    MERCH_ITEM_STORE((short) 0x4CA),
    
    //================================
    // CStoreBankDlg::OnPacket 結束(184-完成)
    //================================
    
    // 猜拳遊戲[完成]
    RPS_GAME((short) 0x4CB),
    
    // 0x4CC
    // 0x4CD
    
    // 聊天室[完成]
    MESSENGER((short) 0x4CE),
    // 玩家互動[完成]
    PLAYER_INTERACTION((short) 0x4CF),
    
    // 0x4D0
    // 0x4D1
    
    //================================
    // CField_Tournament::OnPacket 開始(184-完成)
    //================================
    
    // CField_Tournament::OnTournament[完成]
    TOURNAMENT((short) 0x4D2),
    // CField_Tournament::OnTournamentMatchTable[完成]
    TOURNAMENT_MATCH_TABLE((short) 0x4D3),
    // CField_Tournament::OnTournamentSetPrize[完成]
    TOURNAMENT_SET_PRIZE((short) 0x4D4),
    // CField_Tournament::OnTournamentUEW[完成]
    TOURNAMENT_UEW((short) 0x4D5),
    // CField_Tournament::OnTournamentAvatarInfo[完成]
    TOURNAMENT_CHARACTERS((short) 0x4D6),
    // CField_Wedding::OnWeddingProgress[完成]
    WEDDING_PROGRESS((short) 0x4D7),
    // CField_Wedding::OnWeddingCeremonyEnd[完成]
    WEDDING_CEREMONY_END((short) 0x4D8),

    //================================
    // CCashShop::OnPacket 開始
    //================================ 
    // 0x484 【購物商城合約】
    
    // 0x489
    
    // sub_6E782A {
    // 0x48A
    // 0x48B
    // }
    
    // sub_6A809E {
    // 0x48C
    // 0x48D
    // }
    
    // 0x48E
    // 0x48F
    
    // sub_6A8D7F { 【小鋼珠】
    // 0x490
    // 0x491
    // 0x492
    // }
    
    // 0x493
    // 0x494
    // 0x495
        
    // 宅配操作(完成)
    PACKAGE_OPERATION((short) 0x4E3),

    // 0x4E4
    
    // 購物商城更新[完成-184]
    CS_UPDATE((short) 0x4E5),
    // 購物商城操作[完成]
    CS_OPERATION((short) 0x4E6),
    // CCashShop::OnPurchaseExpChanged[完成]
    CS_EXP_PURCHASE((short) 0x4E7),
    
    // 0x4E8
    // 0x4E9 【00 00 04 00 00 00 00 00 84 11 06 00 00 00 00 00 FF 00 00】
    // 0x4EA
    // 0x4EB
    
    // 購物商城未知[完成]
    CASH_USE4((short) 0x4EC),
    
    // 0x4ED
    // 0x4EE
    // 0x4EF
    // 0x4F0
    // 0x4F1
    // 0x4F2
    // 0x4F3
    // 0x4F4
    // 0x4F5
    
    // 購物商城帳號[完成]
    CS_ACCOUNT((short) 0x4F6),
    // 購物商城未知[完成]
    CASH_USE3((short) 0x4F7),
    // 購物商城未知[完成-182]
    CASH_USE((short) 0x4F8),
    
    // 0x4F9
    // 0x4FA
    // 0x4FB
    // 0x4FC
    // 0x4FD
    
    // 購物商城未知[完成]
    CASH_USE2((short) 0x4FE),
    
    // 0x4FF 【CCashShop::OnMemberShopResult】
    
    // 0x500
    // 0x501
    // 0x502
    
    // 0x503
    // 0x504
    // 0x505
    // 0x506
    // 0x507
    // 0x508 神獸學院
    // 0x509
    // 0x50A
    // 0x50B 羊群牧場[Boolean] 【true => [String][Long]「[String][Long]」* 3】【false => [Int](分數)[Int](排行)[Int](咒文的痕跡)】

    //==================================
    // CFuncKeyMappedMan::OnPacket 開始(184-完成)
    //==================================
    
    // 鍵盤設置[完成]
    KEYMAP((short) 0x526),
    // 寵物技能(HP)[完成]
    PET_AUTO_HP((short) 0x527),
    // 寵物技能(MP)[完成]
    PET_AUTO_MP((short) 0x528),
    // 寵物技能(Buff)[完成]
    PET_AUTO_CURE((short) 0x529),
    
    //================================
    // CLogin::OnPacket 開始(184-完成)
    //================================ 
    
    // 創建角色第二組密碼驗證[完成]【獲得驗證碼 + 第二組密碼錯誤反饋 + 獲得可建立職業】
    CREATE_CHAR_RESPONSE((short) 0x52A),
    
    // 0x52B
    
    //==================================
    // CField::OnGoldHammerRes 開始(184-完成)
    //==================================
    
    // 0x530
    
    //黃金鐵鎚使用完成[完成]
    VICIOUS_HAMMER((short) 0x531),
    
    // 0x532
    
    //==================================
    // CField::OnPlatinumHammerRes 開始(184-完成)
    //==================================
    
    // 0x533
    
    //白金槌子
    PLATINUM_HAMMER((short) 0x534),
    
    // 0x535 
    
    
    //==================================
    // CField::OnZeroScroll 開始(184-完成)
    //==================================
    
    // 0x536
    
    //神之子使用卷軸[完成]
    ZERO_SCROLL_START((short) 0x537),
    //神之子武器介面[完成]
    ZERO_RESULT((short) 0x538),
    //神之子卷軸[完成]
    ZERO_SCROLL((short) 0x539),    
    
    // 0x53A
    // 0x53B
    
    //==================================
    // CField::OnZeroWeapon 開始(184-完成)
    //==================================
    
    //武器資訊
    ZERO_WEAPONINFO((short) 0x53C),    
    //武器成長[完成]
    ZERO_UPGRADE((short) 0x53D),
    
    // 0x53E
    // 0x53F
    
    //==================================
    // CField::OnZeroWeapon 結束
    //==================================
    
    // 0x540    
    // 0x541
    // 0x542
     
    //==================================
    // CField::OnArrowBlaster 開始(184-完成)
    //==================================
     
    // 召喚箭座[完成]
    SPAWN_ARROW_BLASTER((short) 0x543),

    // 0x544 【dispear】

    // 取消箭座[完成]
    CANCEL_ARROW_BLASTER((short) 0x545),

    // 0x546
    // 0x547

    // 箭座控制[完成]
    ARROW_BLASTER_CONTROL((short) 0x548),
    
    // 0x549
    // 0x54A 【破除封印】
    // 0x54B
    // 0x54C
    // 0x54D
    
    //==================================
    // CField::OnArrowBlaster 結束
    //==================================
    
    // sub_50630B {
    // 0x4CC
    // 0x4CD
    // 0x4CE
    // 0x4CF
    // 0x4D0
    // }
    
    // 潛能方塊[完成-184]
    STRENGTHEN_UI((short) 0x59F),
    // 階段介面[完成-184]
    LEVEL_UI((short) 0x5A0),
    
    // sub_6921D9 {
    // 0x54B
    // 0x54C
    // }
     
    // sub_63AD6C {
    // 0x54D [Byte]
    // }


    // 每日免費強化任意門[完成-184]
    DAY_OF_CHRONOSPHERE((short) 0x5BA),
    // 強化任意門錯誤[完成-184]
    ERROR_CHRONOSPHERE((short) 0x5BB),
    
    // sub_6DF4C6 {
    // 0x56D
    // 0x56E
    // 0x56F
    // 0x570
    // 0x571
    // }
    
    // General

    UNKNOWN,
    AUTH_RESPONSE,
    GUEST_ID_LOGIN,
    // Login

    SEND_LINK,
    PIN_OPERATION,
    PIN_ASSIGNED,
    ALL_CHARLIST,
    RELOG_RESPONSE,
    REGISTER_PIC_RESPONSE,
    EXTRA_CHAR_INFO,
    SPECIAL_CREATION,

    // Channel

    FULL_CLIENT_DOWNLOAD,
    BOOK_INFO,
    REPORT_RESULT,
    TRADE_LIMIT,
    UPDATE_GENDER,
    BBS_OPERATION,

    CODEX_INFO_RESPONSE,

    ECHO_MESSAGE,

    BOOK_STATS,
    UPDATE_CODEX,
    CARD_DROPS,
    FAMILIAR_INFO,
    POTION_BONUS,
    
    MAPLE_TV_MSG,
    LUCKY_LUCKY_MONSTORY,
    POPUP2,
    CANCEL_NAME_CHANGE,
    CANCEL_WORLD_TRANSFER,
    CLOSE_HIRED_MERCHANT,
    CANCEL_NAME_CHANGE_2,

    GM_STORY_BOARD,
    FIND_FRIEND,
    VISITOR,
    PINKBEAN_CHOCO,
    
    EQUIP_STOLEN_SKILL,

    INNER_ABILITY_RESET_MSG,
    CASSANDRAS_COLLECTION,

    SET_OBJECT_STATE,
    POPUP,
    YOUR_INFORMATION,

    CANDY_RANKING,
    ATTENDANCE,

    RANDOM_RESPONSE,
    MAGIC_WHEEL,

    

    MOVE_ENV,
    UPDATE_ENV,

    CHATTEXT_1,

    SPAWN_PET_2,

    SPAWN_FAMILIAR,
    MOVE_FAMILIAR,
    TOUCH_FAMILIAR,
    ATTACK_FAMILIAR,
    RENAME_FAMILIAR,
    SPAWN_FAMILIAR_2,
    UPDATE_FAMILIAR,

    R_MESOBAG_SUCCESS,
    R_MESOBAG_FAILURE,
    MAP_FADE,
    MAP_FADE_FORCE,

    RANDOM_EMOTION,
    RADIO_SCHEDULE,
    OPEN_SKILL_GUIDE,

    AP_SP_EVENT,
    QUEST_GUIDE_NPC,
    REGISTER_FAMILIAR,
    FAMILIAR_MESSAGE,

    SHOW_MAP_NAME,
    CAKE_VS_PIE,

    MOVE_SCREEN_X,
    MOVE_SCREEN_DOWN,
    CAKE_PIE_INSTRUMENTS,
    SEALED_BOX,

    //怪物屬性
    MONSTER_PROPERTIES,
    
    //
    CYGNUS_ATTACK,
    //怪物抗擊
    MONSTER_RESIST,

    TELE_MONSTER,
    SHOW_MAGNET,
    
    NPC_TOGGLE_VISIBLE,
    INITIAL_QUIZ,

    RED_LEAF_HIGH,

    LOGOUT_GIFT,
    CS_CHARGE_CASH,
    GIFT_RESULT,
    CHANGE_NAME_CHECK,
    CHANGE_NAME_RESPONSE,
    //0x314 int itemid int sn

    CASH_SHOP_UPDATE,
    GACHAPON_STAMPS,
    FREE_CASH_ITEM,
    CS_SURPRISE,
    XMAS_SURPRISE,
    ONE_A_DAY,
    NX_SPEND_GIFT,
    RECEIVE_GIFT,
    RANDOM_CHECK,

    START_TV,
    REMOVE_TV,
    ENABLE_TV,
    GM_ERROR,
    ALIEN_SOCKET_CREATOR,
    BATTLE_RECORD_DAMAGE_INFO,
    CALCULATE_REQUEST_RESULT,
    BOOSTER_PACK,
    BOOSTER_FAMILIAR,
    BLOCK_PORTAL,
    NPC_CONFIRM,
    RSA_KEY,
    BUFF_BAR,
    GAME_POLL_REPLY,
    GAME_POLL_QUESTION,
    ENGLISH_QUIZ,
    FISHING_BOARD_UPDATE,
    BOAT_EFFECT,
    FISHING_CAUGHT,
    SIDEKICK_OPERATION,
    FARM_PACKET1,
    FARM_ITEM_PURCHASED,
    FARM_ITEM_GAIN,
    HARVEST_WARU,
    FARM_MONSTER_GAIN,
    FARM_INFO,
    FARM_MONSTER_INFO,
    FARM_QUEST_DATA,
    FARM_QUEST_INFO,
    FARM_MESSAGE,
    UPDATE_MONSTER,
    AESTHETIC_POINT,
    UPDATE_WARU,
    FARM_EXP,
    FARM_PACKET4,
    QUEST_ALERT,
    FARM_PACKET8,
    FARM_FRIENDS_BUDDY_REQUEST,
    FARM_FRIENDS,
    FARM_USER_INFO,
    FARM_AVATAR,
    FRIEND_INFO,
    FARM_RANKING,
    SPAWN_FARM_MONSTER1,
    SPAWN_FARM_MONSTER2,
    RENAME_MONSTER,
    //Unplaced:
    DEATH_COUNT,
    REDIRECTOR_COMMAND;

    private short code;
    public static boolean record = false;

    private SendPacketOpcode() {
        this.code = 0x7FFE;
    }

    private SendPacketOpcode(short code) {
        this.code = code;
    }

    @Override
    public void setValue(short code) {
        this.code = code;
    }

    @Override
    public short getValue() {
        return getValue(true);
    }

    public short getValue(boolean show) {
        if (show && ServerConfig.LOG_PACKETS) {
            if (isRecordHeader(this)) {
                record = true;
                String tab = "";
                for (int i = 4; i > name().length() / 8; i--) {
                    tab += "\t";
                }
                FileoutputUtil.log(FileoutputUtil.Packet_Record, "[發送]\t" + name() + tab + "\t包頭:0x" + StringUtil.getLeftPaddedStr(String.valueOf(code), '0', 4) + "\r\n");
            } else {
                record = false;
            }
        }
        return code;
    }

    public String getType(short code) {
        String type = null;
        if (code >= 0 && code < 0xE || code >= 0x17 && code < 0x21) {
            type = "CLogin";
        } else if (code >= 0xE && code < 0x17) {
            type = "LoginSecure";
        } else if (code >= 0x21 && code < 0xCB) {
            type = "CWvsContext";
        } else if (code >= 0xD2) {
            type = "CField";
        }
        return type;
    }

    public static String nameOf(int value) {
        for (SendPacketOpcode opcode : SendPacketOpcode.values()) {
            if (opcode.getValue(false) == value) {
                return opcode.name();
            }
        }
        return "UNKNOWN";
    }

    public static boolean isRecordHeader(SendPacketOpcode opcode) {
        switch (opcode.name()) {
//            case "WARP_TO_MAP":
//            case "GUILD_OPERATION":
//            case "PARTY_OPERATION":
//            case "GIVE_BUFF":
//            case "SPAWN_PLAYER":
//            case "DROP_ITEM_FROM_MAPOBJECT":
//            case "INVENTORY_OPERATION":
//            case "SPAWN_MONSTER":
            case "UNKNOWN":
                return true;
            default:
                return false;
        }
    }

    public static boolean isSpamHeader(SendPacketOpcode opcode) {
        switch (opcode.name()) {
            case "WARP_TO_MAP":
            case "PING":
            case "NPC_ACTION":
//            case "AUTH_RESPONSE":
//            case "SERVERLIST":
            case "UPDATE_STATS":
            case "MOVE_PLAYER":
            case "SPAWN_NPC":
            case "SPAWN_NPC_REQUEST_CONTROLLER":
            case "REMOVE_NPC":
            case "MOVE_MONSTER":
            case "MOVE_MONSTER_RESPONSE":
            case "SPAWN_MONSTER":
            case "SPAWN_MONSTER_CONTROL":
//            case "HAKU_MOVE":
//            case "MOVE_SUMMON":
//            case "MOVE_FAMILIAR":
            case "ANDROID_MOVE":
//            case "INVENTORY_OPERATION":
//            case "MOVE_PET":
//            case "SHOW_SPECIAL_EFFECT":
//            case "DROP_ITEM_FROM_MAPOBJECT":
//            case "REMOVE_ITEM_FROM_MAP":
//            case "UPDATE_PARTYMEMBER_HP":
//            case "DAMAGE_PLAYER":
//            case "SHOW_MONSTER_HP":
//            case "CLOSE_RANGE_ATTACK":
//            case "RANGED_ATTACK":
//            case "ARAN_COMBO":
//            case "REMOVE_BG_LAYER":
//            case "SPECIAL_STAT":
//            case "TOP_MSG":
//            case "ANGELIC_CHANGE":
//            case "UPDATE_CHAR_LOOK":
//            case "KILL_MONSTER":
            case "SYSTEM_PROCESS_LIST":
                return true;
            default:
                return false;
        }
    }

    public static void loadValues() {
        Properties props = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("sendops.properties")) {
            props.load(new BufferedReader(new InputStreamReader(fileInputStream, EncodingDetect.getJavaEncode("sendops.properties"))));
        } catch (IOException ex) {
            InputStream in = SendPacketOpcode.class.getClassLoader().getResourceAsStream("sendops.properties");
            if (in == null) {
                System.out.println("未加載 sendops.properties 檔案, 使用內置SendPacketOpcode Enum");
                return;
            }
            try {
                props.load(in);
            } catch (IOException e) {
                throw new RuntimeException("加載 sendops.properties 檔案出現錯誤", e);
            }
        }
        ExternalCodeTableGetter.populateValues(props, values());
    }

    static {
        loadValues();
    }
}