/**
 * Created by Kristian Nielsen on 02-04-2018.
 */
var assert = require('assert');
const defaultSquares = require('./default-squares');
const defaultOptions = require('./default-options');
const types = require('./type-consts');

const Square = require('./square');
const Player = require('../player/player');
const Board = require('../board/board');

describe('Square', function() {
    describe('#onVisit(player)', function() {
        it('Executes when players visit a square. Should perform the appropriate actions based on the square type', function() {
            const square = new Square(defaultOptions.getStartSquareOptions());
            const player = new Player('Jon');

            const previousBalance = player.balance;

            assert.equal(square.type, types.START_SQUARE, "Tried to generate start square, but generated square had incorrect type");
            assert.equal(square._balanceChangeOnVisit, 200, "Tried to generate start square, but generated square had incorrect _balanceChangeOnVisit");

            square.onVisit(player);

            assert.equal(player.balance, previousBalance + 200, "Player balance was incorrectly updated after visitting the start square");
        });
    });
    describe('#onVisit(player) - Buildable property with different owner', function() {
        it('Should transfer money from player (Jon) to owner (Ralph)', function() {
            const square = new Square();
            const player_jon = new Player('Jon');
            const player_ralph = new Player('Ralph');

            square.owner = player_ralph;
            square._balanceChangeOnVisit = -500;
            square.type = types.BUILDABLE_PROPERTY;

            square.onVisit(player_jon);

            assert.equal(player_jon.balance, 1000, 'Jons balance should be 1000 (1500 - 500)');
            assert.equal(player_jon.balance, 1000, 'Ralphs balance should be 2000 (1500 + 500)');

        });
    });
    describe('#onVisit(player) - Buildable owned property', function() {
        it('Should not alter balance of player (Jon)', function() {
            const square = new Square();
            const player_jon = new Player('Jon');

            const previousBalance_jon = player_jon.balance;

            square.owner = player_jon;
            square._balanceChangeOnVisit = -500;
            square.type = types.BUILDABLE_PROPERTY;

            square.onVisit(player_jon);

            assert.equal(player_jon.balance, previousBalance_jon, 'Jons balance should be 1500 (unaltered)');
        });
    });
    describe('#onVisit(player) - Train, different owner (owns both)', function() {
        it('Should not alter balance of player (Jon)', function() {
            const board = new Board();

            const player_jon = new Player('Jon');
            const player_ralph = new Player('Ralph');

            board._dies[0].value = 6;
            board._dies[1].value = 6;

            // we get the two utility squares
            const utilSquares = board.getSquaresInGroup('utility');

            // we set player_ralph as the owner of both
            utilSquares.forEach(s => {
                s.owner = player_ralph;
            });

            // die values times cost-factor times number of utility type squares owned:
            const calculated_rent = 12 * 5 * 2;

            utilSquares[0].onVisit(player_jon, board);

            assert.equal(player_jon.balance, 1500 - calculated_rent);
            assert.equal(player_ralph.balance, 1500 + calculated_rent);
        });
    });
});

describe('DefaultSquares', function() {
    describe('#exportedFunc()', function() {
        it('Should return a 40-length array', function() {
            const squares = defaultSquares();

            assert.equal(squares.length, 40, "The default board should contain 40 squares!");
        });
        it('Should return elements that are instances of the Square type', function(){
            const squares = defaultSquares();

            squares.forEach(s => {
                assert(s instanceof Square, "Not all elements were instances of the Square type")
            })
        })
    });
});