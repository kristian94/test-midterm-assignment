/**
 * Created by Kristian Nielsen on 02-04-2018.
 */
module.exports = Player;

function Player(name){
    this.name = name;
    this.balance = 1500;
    this.position = 0;
}

Player.prototype._move = function(board, distance){
    // if new position is greater than squares.length, reduce position
    // by squares.length
    const proposedNextPosition = this.position + distance;
    const subtract = proposedNextPosition > board._squares.length;

    this.position = subtract ?
        proposedNextPosition - board._squares.length :
        proposedNextPosition

};

Player.prototype.takeTurn = function(board){
    const distance = board.rollDies();
    this._move(board, distance);
    const square = board.getSquare(this.position);
    square.onVisit(this, board);
};