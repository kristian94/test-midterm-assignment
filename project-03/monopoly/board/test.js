var assert = require('assert');
const Board = require('./board');
const Square = require('../square/square');
const types = require('../square/type-consts');

describe('Board', function() {
    describe('#rollDies()', function() {
        it('Should return a value between 2 and 12', function() {
            const board = new Board();
            const roll = board.rollDies();
            assert(roll => 2 && roll <= 12, 'Roll was out of bounds (2-12)');
        });
    });

    describe('#getSquare()', function(){
        it('Should return the square at the given position', function(){
            const board = new Board();

            const square = board.getSquare(0);
            assert(board.getSquare(getRandomInt(0, 39)) instanceof Square, 'Returned object was not an instance of Square');
            assert(board.getSquare(0).type == types.START_SQUARE, 'Square at position 0 was not of type: START_SQUARE');
        })
    });

    describe('#getSquaresInGroup(group)', function(){
        it('Should return all squares that belong to a given group', function(){
            const board = new Board();

            const group_1 = board.getSquaresInGroup(1);
            const group_2 = board.getSquaresInGroup(2);
            const group_trains = board.getSquaresInGroup('train');
            const group_utilities = board.getSquaresInGroup('utility');

            assert.equal(group_1.length, 2);
            assert.equal(group_2.length, 3);
            assert.equal(group_trains.length, 4);
            assert.equal(group_utilities.length, 2);
        })
    });
    describe('#getVisitJailPosition()', function(){
        it('Should return the position of the jail square (using the deault square setup)', function(){
            const board = new Board();

            assert.equal(board.getVisitJailPosition(), 10);
        })
    })
});

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max + 1 - min)) + min;
}