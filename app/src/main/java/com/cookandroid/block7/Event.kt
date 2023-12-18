package com.cookandroid.block7

// Event: MainGame, 이벤트 이름, 호출 가능 여부, 가중치
open class Event(GameActivity: GameActivity, eventName: String,  weight: Int, isAvailable: Boolean) {
    // 게임 액티비티
    var GameActivity: GameActivity = GameActivity
    // 이벤트 이름
    var eventName: String = eventName
    // 가중치
    var weight: Int = weight
    // 호출 가능 여부
    var isAvailable: Boolean = isAvailable

    private val eventScripts: Map<String, String> = readEventScriptsFromResources(R.raw.event_scripts)

    // 스크립트
    private var preScript: String = ""
    private var postScript: String = ""

    init {
        setIsAvailable()
    }

    // isAvailable 및 weight 수정 메소드
    open fun setIsAvailable() {

    }

    // 이벤트 효과 메소드
    open fun eventEffect(tmp: Int) {

    }

    /* 이벤트 실행 메소드 */
    fun executeEventEffect() {
        // 아이템, 식량, 멤버
        val itemListTmp = GameActivity.itemList
        val itemListOwnedTmp = GameActivity.itemListOwned
        val itemListBrokenTmp = GameActivity.itemListBroken
        val kimbabTmp = GameActivity.food_kimbap
        val waterTmp = GameActivity.food_water
        val memberListTmp = GameActivity.memberList

        /* 이벤트 실행 */
        var tmp = 1
        eventEffect(tmp)

        /* postScript 수정 */
        // 아이템
        for(item in itemListTmp) {
            if(item in itemListOwnedTmp) { // 원래 가지고 있던 경우
                if(item in GameActivity.itemListBroken) { postScript += item.nameKorItem + "이(가) 고장났다.\n" }
                if(item !in GameActivity.itemListOwned) { postScript += item.nameKorItem + "을(를) 잃었다.\n" }
            } else { // 원래 가지고 있지 않았던 경우
                if(item in GameActivity.itemListOwned) { postScript += item.nameKorItem + "을(를) 얻었다.\n" }
            }
        }

        // 식량
        val numKimbab = GameActivity.food_kimbap.count - kimbabTmp.count
        val numWater = GameActivity.food_water.count - waterTmp.count
        if(numKimbab > 0) { postScript += "김밥을 " + numKimbab +" 개 얻었다.\n" }
        else if(numKimbab < 0) { postScript += "김밥을 " + Math.abs(numKimbab) +" 개 잃었다.\n" }
        if(numWater > 0) { postScript += "물을 " + numWater +" 개 얻었다.\n" }
        else if(numWater < 0) { postScript += "물을 " + Math.abs(numWater) +" 개 잃었다.\n" }

        // 멤버
        for(memberTmp in memberListTmp) {
            val member = GameActivity.memberList.find { it.name == memberTmp.name }
            if(memberTmp.getStateIsCrazy() != member?.getStateIsCrazy()) {
                // isVeryCarzy -> isCrazy로 바뀐 경우, 여기에 걸림. "멤버이(가) 미쳤습니다" 표기
                if(member?.getStateIsCrazy() == true) postScript += member?.nameKor + "이(가) 미쳤습니다.\n"
                if(member?.getStateIsCrazy() == false && member?.getStateIsVeryCrazy() == false) postScript += member?.nameKor + "이(가) 정상으로 돌아왔습니다."
            }
            if(memberTmp.getStateIsVeryCrazy() != member?.getStateIsVeryCrazy()) {
                if(member?.getStateIsVeryCrazy() == true) postScript += member?.nameKor + "이(가) 착란 상태 입니다.\n"
                if(member?.getStateIsVeryCrazy() == false && member?.getStateIsCrazy() == false) postScript += member?.nameKor + "이(가) 정상으로 돌아왔습니다."
            }
            if(memberTmp.getStateIsHurt() != member?.getStateIsHurt()) {
                if(member?.getStateIsHurt() == true) postScript += member?.nameKor + "이(가) 다쳤습니다.\n"
                if(member?.getStateIsHurt() == false && member?.getStateIsVeryHurt() == false) postScript += member?.nameKor + "이(가) 정상으로 돌아왔습니다."
            }
            if(memberTmp.getStateIsVeryHurt() != member?.getStateIsVeryHurt()) {
                if(member?.getStateIsVeryHurt() == true) postScript += member?.nameKor + "이(가) 고통받습니다.\n"
                if(member?.getStateIsVeryHurt() == false && member?.getStateIsHurt() == false) postScript += member?.nameKor + "이(가) 정상으로 돌아왔습니다."
            }
            if(memberTmp.getStateIsSick() != member?.getStateIsSick()) {
                if(member?.getStateIsCrazy() == true) postScript += member?.nameKor + "이(가) 질병에 걸렸습니다.\n"
                if(member?.getStateIsCrazy() == false && member?.getStateIsVeryCrazy() == false) postScript += member?.nameKor + "이(가) 정상으로 돌아왔습니다."
            }
            if(memberTmp.getStateIsVerySick() != member?.getStateIsVerySick()) {
                if(member?.getStateIsCrazy() == true) postScript += member?.nameKor + "이(가) 병에 걸렸습니다.\n"
                if(member?.getStateIsCrazy() == false && member?.getStateIsVeryCrazy() == false) postScript += member?.nameKor + "이(가) 정상으로 돌아왔습니다."
            }
            if(memberTmp.getStateIsTired() != member?.getStateIsTired()) {
                if(member?.getStateIsCrazy() == true) postScript += member?.nameKor + "은(는) 피곤합니다.\n"
                if(member?.getStateIsCrazy() == false && member?.getStateIsVeryCrazy() == false) postScript += member?.nameKor + "이(가) 정상으로 돌아왔습니다."
            }
            if(memberTmp.getStateIsFatigued() != member?.getStateIsFatigued()) {
                if(member?.getStateIsCrazy() == true) postScript += member?.nameKor + "이(가) 지쳤습니다.\n"
                if(member?.getStateIsCrazy() == false && member?.getStateIsVeryCrazy() == false) postScript += member?.nameKor + "이(가) 정상으로 돌아왔습니다."
            }
        }

        // 스크립트 추가 부분 구현
    }


    /* 랜덤 이벤트 */
    // 랜덤으로 가지고 있는 아이템 중 하나를 잃
    fun loseRandomItem() {
        var selectedItem: Item? = GameActivity.getRandomItemitemListOwned()
        if(selectedItem != null) selectedItem.loseItem()
    }
    // 랜덤으로 식량을 잃고, 식량 이름과 잃은 개수를 반환함
    fun loseRandomFood(start: Int, end: Int){ // < 식량 객체, 잃은 개수 > 반환
        val selectedFood: Food = GameActivity.getRandomFood() // 랜덤 식량
        selectedFood.loseFoodRandom(start, end) // 해당 식량이 0개인 경우 loseFoodRandom 내부에서 0개로 조정됨. 변경사항 없음.
    }
    // 랜덤 멤버 상태 변화
    fun crazyRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeCrazy()
    }
    fun veryCrazyRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeVeryCrazy()
    }
    fun hurtRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeHurt()
    }
    fun veryHurtRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeVeryHurt()
    }
    fun sickRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeSick()
    }
    fun verySickRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeVerySick()
    }
    fun tiredRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeTired()
    }
    fun fatiguedRandomMember() {
        val selectdeMember: Member = GameActivity.getRandomMemberFromListIsIn()
        selectdeMember.stateChangeFatigued()
    }


    // 스크립트 설정 메소드
    fun setPreScript(str: String) { preScript = str }
    fun setPostScript(str: String) { postScript = str }
    // preScript를 반환하는 메소드
    fun getPreScript(): String {
        return preScript
    }

    fun setPostScriptNull() { postScript = "" } // 아무일도 일어나지 않을 경우 호출
    fun addPostScriptLoseNothing(str: String) { postScript += str }
    fun addPostScriptLoseItem(itemName: String) { postScript += itemName + "을(를) 잃었습니다.\n" }
    fun addPostScriptLoseFood(foodName: String, cnt: Int) { postScript += foodName + "을(를) " + cnt.toString() +"개 잃었습니다.\n" }
    fun addPostScriptHurtMamber(memberName: String) { postScript += memberName + "이(가) 다쳤습니다.\n" }

    fun getScript(eventKey: String): String {
        return eventScripts[eventKey] ?: ""
    }

    private fun readEventScriptsFromResources(resourceId: Int): Map<String, String> {
        return try {
            val scriptText = GameActivity.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
            parseEventScripts(scriptText)
        } catch (e: Exception) {
            mapOf()
        }
    }

    private fun parseEventScripts(scriptText: String): Map<String, String> {
        val scripts = mutableMapOf<String, String>()
        val lines = scriptText.split("\n")

        var currentEventKey: String? = null
        var currentEventScript: StringBuilder? = null

        for (line in lines) {
            if (line.startsWith("event_")) {
                currentEventKey?.let { key ->
                    currentEventScript?.let { script ->
                        scripts[key] = script.toString()
                    }
                }
                currentEventKey = line.trim()
                currentEventScript = StringBuilder()
            } else {
                currentEventScript?.append(line)?.append("\n")
            }
        }

        currentEventKey?.let { key ->
            currentEventScript?.let { script ->
                scripts[key] = script.toString()
            }
        }

        return scripts
    }
}