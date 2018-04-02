module.exports = Square;

const types = require('./type-consts');

function Square(options = {}){
    this.owner = null;

    // options
    this.name = options.name;
    this.type = options.type;
    this._balanceChangeOnVisit = options._balanceChangeOnVisit; //number
    this.group = options.group;

    // this.numberOfHouses = 0;
    // this.hasHotel = () => this.numberOfHouses == 5;
}

Square.prototype.onVisit = function(player, board){
    this._visitHandlers[this.type].call(this, player, board);
};

Square.prototype._visitHandlers = {
    [types.START_SQUARE]: function(player, board){
        player.balance += this._balanceChangeOnVisit;
    },
    [types.BUILDABLE_PROPERTY]: function(player, board){
        if(this.owner !== null && this.owner !== player){
            player.balance += this._balanceChangeOnVisit;
            this.owner.balance -= this._balanceChangeOnVisit;
        }
    },
    [types.FREE]: function(player, board){

    },
    [types.CHANCE_CARD]: function(player, board){
        // todo
    },
    [types.COMMUNITY_CHEST]: function(player, board){
        // todo
    },
    [types.JAIL]: function(player, board){
        player.inJail = true;
        player.position = board.getVisitJailPosition();
    },
    [types.TAX]: function(player, board){
        player.balance += this._balanceChangeOnVisit;
    },
    [types.TRAIN]: function(player, board){
        // todo
    },
    [types.UTILITY]: function(player, board){
        if(this.owner !== null && this.owner !== player){
            const dieSum = board.getCurrentDieSum();
            const ownedUtils = board._squares.filter(s => s.type == types.UTILITY && s.owner == this.owner);

            console.log(dieSum, ownedUtils.length);

            const amountToPay = dieSum * 5 * ownedUtils.length;

            player.balance -= amountToPay;
            this.owner.balance += amountToPay;
        }
    }

};
