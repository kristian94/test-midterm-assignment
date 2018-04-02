/**
 * Created by Kristian Nielsen on 02-04-2018.
 */
module.exports = Dice;

function Dice(){
    this.min = 1;
    this.max = 6;
    this.value = 0;
}

Dice.prototype.roll = function(){
    this.value = getRandomInt(this.min, this.max);
};

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max + 1 - min)) + min;
}