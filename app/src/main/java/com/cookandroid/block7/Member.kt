package com.cookandroid.block7

import android.view.View
import android.widget.ImageView

class Member(GameActivity: GameActivity, name: String, nameKor: String, memberView: ImageView) {
    // 게임 엑티비티
    val GameActivity: GameActivity = GameActivity

    // 멤버 정보
    var name: String = name
    var nameKor: String = nameKor
    var memberView: ImageView = memberView

    var memberScript: String = ""

    // 살아있는지
    private var isAlive : Boolean = true

    // level : 배고픔, 목마름, 멘탈
    private var levelHunger: Int = 100
    private var levelThirst: Int = 100
    private var levelMental: Int = 100

    // state : 게임에 나타날 String
    private var stateHunger : String = ""
    private var stateThirst : String = ""
    private var stateMental: String = ""
    private var stateHurt: String =""
    private var stateSick: String = ""
    private var stateTired: String = ""
    private var stateFatigued: String = ""

    // 상태 변화
    private var isCrazy: Boolean = false
    private var isVeryCrazy: Boolean = false
    private var isHurt: Boolean = false
    private var isVeryHurt: Boolean = false
    private var isSick: Boolean = false
    private var isVerySick: Boolean = false
    private var isTired: Boolean = false
    private var isFatigued: Boolean = false

    private var dateCrazy: Int = 0
    private var dateVeryCrazy: Int = 0
    private var dateHurt: Int = 0
    private var dateVeryHurt: Int = 0
    private var dateSick: Int = 0
    private var dateVerySick: Int = 0
    private var dateTired: Int = 0
    private var dateFatigued: Int = 0

    // 상태변화 - 특수
    private var isAlien: Boolean = false
    private var isBrainwashing: Boolean = false

    // 탐험 관련?
    private var isIn : Boolean = true // 안에 있는가?
    private var isExploring = false // 탐험중인가?
    private var dateExplore: Int = 0


    /* 멤버 메소드 */

    // 하루가 지날때마다 항상 호출되는 메소드. 굶주림 레벨, 목마름 레벨 감소
    fun dayPase() {
        levelHunger = maxOf(0, levelHunger - 10)
        levelThirst = maxOf(0, levelThirst - 15)

        /* 나머지 부분 구현 */

        updateState() // 상태 업데이트
        updateVisibility() // 이미지 업데이트
    }

    // 멤버 상태 업데이트 메소드
    fun updateMember() {
        updateState() // 상태 업데이트
        updateVisibility() // 이미지 업데이트
        updateDate() // 날짜 업데이트
        updateScript() // 스크립트 업데이트
    }

    // 먹는 메소드
    fun eatKimbab() {

    }

    fun drinkWater() {

    }

    // 탐험 - 갈 때 메소드
    fun goExploring() {
        if(!isAlive) return; // 살아있지 않다면 return
        isExploring = true
        isIn = false
        GameActivity.memberListIsIn.remove(this)
    }

    // 탐험 - 돌아올 때 메소드
    fun returnExploring() {
        if(!isAlive) return; // 살아있지 않다면 return
        isIn = true
        GameActivity.memberListIsIn.add(this)
    }

    // 죽음 메소드
    fun die() {
        isExploring = false
        isAlive = false
    }

    // 상태 변화 메소드
    fun stateChangeCrazy() { isCrazy = true }
    fun stateChangeVeryCrazy() { isVeryCrazy = true }
    fun stateChangeHurt() { isHurt = true }
    fun stateChangeVeryHurt() { isVeryHurt = true }
    fun stateChangeSick() { isSick = true }
    fun stateChangeVerySick() { isVerySick = true }
    fun stateChangeTired() { isTired = true }
    fun stateChangeFatigued() { isFatigued = true }

    // 상태 가져오기 - Boolean
    fun getStateIsCrazy(): Boolean { return isCrazy }
    fun getStateIsVeryCrazy(): Boolean { return isVeryCrazy }
    fun getStateIsHurt(): Boolean { return isHurt }
    fun getStateIsVeryHurt(): Boolean { return isVeryHurt }
    fun getStateIsSick(): Boolean { return isSick }
    fun getStateIsVerySick(): Boolean { return isVerySick }
    fun getStateIsTired(): Boolean { return isTired }
    fun getStateIsFatigued(): Boolean { return isFatigued }


    // 상태 가져오기 - String
    fun getMemberState(): String { // 모든 상태 가져오기 - GameActivity에서 호출?
        return getStateHunger() + '\n' + getStateThirst() + '\n' + getStateMental() + '\n' + getStateHurt() + '\n' + getStateSick() + '\n' + getStateTired() + '\n' + getStateFatigued()
    }
    fun getStateHunger(): String { return stateHunger }
    fun getStateThirst(): String { return stateThirst }
    fun getStateMental(): String { return stateMental }
    fun getStateHurt(): String { return stateHurt }
    fun getStateSick(): String { return stateSick }
    fun getStateTired(): String { return stateTired }
    fun getStateFatigued(): String { return stateFatigued }


    /* update 메소드 */

    // 멤버 Script 업데이트.
    fun updateScript() {
        if (isCrazy) memberScript += nameKor + ""
        if (isVeryCrazy) memberScript += ""
        if (isHurt) memberScript += ""
        if (isVeryHurt) memberScript += ""
        if (isSick) memberScript += ""
        if (isVerySick) memberScript += ""
        if (isTired) memberScript += ""
        if (isFatigued) memberScript += ""
        if (!isIn) memberScript += ""
        if(stateHunger == "배고픔") memberScript += ""
        else if(stateHunger == "굶주림") memberScript += ""
        if(stateThirst == "목마름") memberScript += ""
        else if(stateThirst == "탈수") memberScript += ""
    }

    // date 업데이트 - 수정 필요, 임의로 작성함
    fun updateDate() {
        if (isCrazy) dateCrazy++
        if (isVeryCrazy) dateVeryCrazy++
        if (isHurt) dateHurt++
        if (isVeryHurt) dateVeryHurt++
        if (isSick) dateSick++
        if (isVerySick) dateVerySick++
        if (isTired) dateTired++
        if (isFatigued) dateFatigued++
        if (!isIn) dateExplore++ // 안에 있지 않다면, dateExplore++
    }

    // 멤버의 이미지 업데이트 메소드
    fun updateVisibility() {
        // 살아있고, 탐험 가지 않았다면 보이게
        if(isAlive && isIn) memberView.visibility = View.VISIBLE
        // 살아있지 않거나, 탐험 갔다면 보이지 않게
        else if(!isAlive || !isIn) memberView.visibility = View.GONE
        // 상태 변화 추가 (미침, 지침, 다침, 외계인 등 ..)
    }

    // state(String) 업데이트
    fun updateState() {
        // stateHunger: 100~71 공백, 70~31 배고픔, 30~0 굶주림
        stateHunger = when {
            levelHunger > 70 -> ""
            levelHunger > 30 -> "배고픔"
            else -> "굶주림" }
        // stateThirst: 100~71 공백, 70~26 목마름, 25~0: 탈수
        stateThirst = when {
            levelThirst > 70 -> ""
            levelThirst > 25 -> "목마름"
            else -> "탈수" }
        // stateMental: isCrazy 미침, isVeryCrazy 착란
        stateMental = when {
            isCrazy -> "미침"
            isVeryCrazy -> "착란"
            else -> "" }
        // stateHurt: isHurt 다침, isVeryHurt 고통
        stateHurt = when {
            isHurt -> "다침"
            isVeryHurt -> "고통"
            else -> "" }
        // stateSick: isSick 질병, isVerySick 병
        stateSick = when {
            isSick -> "질병"
            isVerySick -> "병"
            else -> "" }
        // stateTired: isTired 피곤함
        stateTired = when {
            isTired -> "피곤함"
            else -> "" }
        // stateFatigued: isFatigued 지침
        stateFatigued = when {
            isFatigued -> "지침"
            else -> "" }
    }
}