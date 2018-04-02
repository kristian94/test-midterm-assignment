/**
 * Created by Kristian Nielsen on 02-04-2018.
 */

const types = require('./type-consts');

module.exports = {
    getStartSquareOptions: function(){
        return {
            name: 'Start',
            _balanceChangeOnVisit: 200,
            type: types.START_SQUARE
        }
    },
    getBuildablePropertyOptions: function(name, cost, group){
        return {
            name: name,
            type: types.BUILDABLE_PROPERTY,
            cost: cost,
            housePrice: cost / 2,
            group: group
        }
    },
    getChanceCardOptions: function(){
        return {
            name: 'Chance Card',
            type: types.BUILDABLE_PROPERTY
        }
    },
    getCommunityChestOptions: function(){
        return {
            name: 'Coomunity Chest',
            type: types.COMMUNITY_CHEST
        }
    },
    getTrainOptions: function(name){
        return {
            name: name,
            type: types.TRAIN,
            cost: 200,
            group: 'train'
        }
    },
    getUtilityOptions: function(name){
        return {
            name: name,
            type: types.UTILITY,
            cost: 150,
            group: 'utility'
        }
    },
    getJailOptions: function(){
        return {
            name: 'Go To Jail',
            type: types.JAIL
        }
    },
    getTaxOptions: function(name, balanceChangeOnVisit){
        return {
            name: name,
            _balanceChangeOnVisit: balanceChangeOnVisit,
            type: types.TAX
        }
    },
    getFreeOptions: function(name){
        return {
            name: name,
            type: types.FREE
        }
    },
    getVisitJailOptions: function(){
        return {
            name: 'Visit Jail',
            type: types.VISIT_JAIL
        }
    }
};