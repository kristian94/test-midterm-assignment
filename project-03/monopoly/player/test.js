/**
 * Created by Kristian Nielsen on 02-04-2018.
 */
var assert = require('assert');
const Board = require('../board/board');
const Player = require('./player');


describe('Player', function() {

    describe('#Player(name)', function(){
        it('Should return a new instance of Player', function(){
            const player = new Player('Jon');
            assert(player.name == 'Jon', 'player name was incorrectly set');
            assert(player.balance == 1500, 'player balance was incorrectly set');
        })
    });
    describe('#_move(board, distance)', function() {
        it('Should update the players position based on distance', function() {
            const board = new Board();
            const player = new Player('Jon');

            assert.equal(board._squares.length, 40, "For consistency, we need to test with a 40 square board");

            player._move(board, 50);
            assert.equal(player.position, 10, "Player position was incorrectly updated");
        });
    });
    describe('#takeTurn(board)', function(){
        it('Should process a turn for the given player (roll the dice, update player position)', function(){
            const board = new Board();
            const player = new Player('Jon');

            player.takeTurn(board);
            diceValueSum = board._dies.reduce((acc, curr)=>{return acc + curr.value}, 0);
            assert.equal(player.position, diceValueSum);
        })
    });
});