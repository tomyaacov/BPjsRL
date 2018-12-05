
var MoveEventSet = [bp.Event("Up"), bp.Event("Down"), bp.Event("Left"), bp.Event("Right")];

var mine = [
    [1, 1],
    [4, 1],
    [0, 3],
    [3, 3]
];
var power = [
    [2, 0],
    [2, 2],
    [1, 4],
    [5, 2]
];
var goal = [
    [4, 4]
];
var not_regular = mine.concat(power, goal);
var terminate = goal.concat(mine)

bp.registerBThread( "initialize", function(){
    mazeState.setX(0);
    mazeState.setY(0);
} );

bp.registerBThread( "actions", function(){
    while (true){
        bp.sync( {request: MoveEventSet} );
    }
} );

bp.registerBThread( "move", function(){
    while (true){
        var e = bp.sync({ waitFor : bp.all });
        if(e.getName() == "Up") {
            if (mazeState.getY() > 0) {
                mazeState.setY(mazeState.getY() - 1);
            }
        }
        if(e.getName() == "Down") {
            if(mazeState.getY() < 5){
                mazeState.setY(mazeState.getY()+1);
            }
        }
        if(e.getName() == "Left") {
            if(mazeState.getX() > 0){
                mazeState.setX(mazeState.getX()-1);
            }
        }
        if(e.getName() == "Right") {
            if(mazeState.getX() < 6){
                mazeState.setX(mazeState.getX()+1);
            }
        }
        bp.sync({ request: bp.Event("updateDone"), block: MoveEventSet });
    }
} );

function isItemInArray(array, item) {
    for (var i = 0; i < array.length; i++) {
        if (array[i][0] == item[0] && array[i][1] == item[1]) {
            return true;
        }
    }
    return false;
}

bp.registerBThread( "rewards", function(){
    while (true){
        bp.sync( {waitFor:bp.all} );
        bp.sync( {waitFor:bp.Event("updateDone")} );

        if(isItemInArray(mine, [mazeState.getX(), mazeState.getY()])){
            mazeState.insertReward(-100);
        }
        if(isItemInArray(power, [mazeState.getX(), mazeState.getY()])){
            mazeState.insertReward(1);
        }
        if(isItemInArray(goal, [mazeState.getX(), mazeState.getY()])){
            mazeState.insertReward(100);
        }
        if(!isItemInArray(not_regular, [mazeState.getX(), mazeState.getY()])){
            mazeState.insertReward(-1);
        }

    }
} );

bp.registerBThread( "terminate", function(){
    while (true){
        if(isItemInArray(terminate, [mazeState.getX(), mazeState.getY()])){
            bp.sync( {block:bp.all} );
        } else {
            bp.sync( {waitFor:bp.all} );
            bp.sync( {waitFor:bp.Event("updateDone")} );
        }
    }
} );




