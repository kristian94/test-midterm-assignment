/**
 * Created by Kristian Nielsen on 02-04-2018.
 */
var assert = require('assert');
const Dice = require('./dice');


describe('Dice', function() {
    describe('#roll()', function() {
        it('Should return a value between 1 and 6', function() {
            const dice = new Dice();
            dice.roll();
            assert(dice.value >= 1 && dice.value <= 6, 'Dice roll produced out of bounds value');

        });
    });
});