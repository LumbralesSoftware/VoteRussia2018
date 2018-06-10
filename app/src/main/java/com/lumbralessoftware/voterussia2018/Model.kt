package com.lumbralessoftware.voterussia2018

/**
 * Created by javiergonzalezcabezas on 7/6/18.
 */
class Player {
    private var club: String? = null
    private var id: Int? = null
    private var imageURL: String? = null
    private var name: String? = null
    private var number: String? = null
    private var position: Int? = null
    private var team: String? = null
    private var vote: String? = null

    fun getClub(): String? {
        return club
    }

    fun setClub(club: String) {
        this.club = club
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getImageURL(): String? {
        return imageURL
    }

    fun setImageURL(imageURL: String) {
        this.imageURL = imageURL
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getNumber(): String? {
        return number
    }

    fun setNumber(number: String) {
        this.number = number
    }

    fun getPosition(): Int? {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    fun getTeam(): String? {
        return team
    }

    fun setTeam(team: String) {
        this.team = team
    }

    fun getVote(): String? {
        return vote
    }

    fun setVote(vote: String?) {
        this.vote = vote
    }

}

class Vote {

    var sum: Int? = null
    var id: Int? = null
    var one: Int? = null
    var two: Int? = null
    var three: Int? = null
    var four: Int? = null
    var five: Int? = null
    var total: Int? = null

}