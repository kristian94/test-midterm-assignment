/**
 * Created by Kristian Nielsen on 02-04-2018.
 */
const defaultSquares = require('../square/default-squares');
const types = require('../square/type-consts');
const Dice = require('../dice/dice');
module.exports = Board;

function Board(options = {}){
    this._squares = options.sqaures || defaultSquares();
    this._dies = [new Dice(), new Dice()];
}

Board.prototype.rollDies = function(){
    this._dies.forEach(d => d.roll());
    return this._dies.reduce((acc, curr) => {
        return acc + curr.value
    }, 0)
};

Board.prototype.getSquare = function(position){
    return this._squares[position];
};

Board.prototype.getCurrentDieSum = function(){
    return this._dies.reduce((acc, curr) => {return acc + curr.value}, 0)
};

Board.prototype.getVisitJailPosition = function(){
    let pos = 0;
    this._squares.forEach((s, i) => {
        if(s.type == types.VISIT_JAIL){
            pos = i;
        }
    });
    return pos;
};

Board.prototype.getSquaresInGroup = function(group){
    // returns all squares that belong to a given group
    return this._squares.reduce((acc, curr) => {
        if(curr.group == group){
            acc.push(curr)
        }
        return acc;
    }, [])
};




