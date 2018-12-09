
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
var terminate = goal.concat(mine);

bp.registerBThread( "initialize", function(){
    mazeWorld.setX(0);
    mazeWorld.setY(0);
} );

bp.registerBThread( "actions", function(){
    while (true){
        bp.sync( {request: MoveEventSet} );
    }
} );

bp.registerBThread( "move", function(){
    while (true){
        var e = bp.sync({ waitFor : MoveEventSet });
        if(e.getName() == "Up") {
            if (mazeWorld.getY() > 0) {
                mazeWorld.setY(mazeWorld.getY() - 1);
            }
        }
        if(e.getName() == "Down") {
            if(mazeWorld.getY() < 4){
                mazeWorld.setY(mazeWorld.getY()+1);
            }
        }
        if(e.getName() == "Left") {
            if(mazeWorld.getX() > 0){
                mazeWorld.setX(mazeWorld.getX()-1);
            }
        }
        if(e.getName() == "Right") {
            if(mazeWorld.getX() < 5){
                mazeWorld.setX(mazeWorld.getX()+1);
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
        if(isItemInArray(mine, [mazeWorld.getX(), mazeWorld.getY()])){
            mazeWorld.insertReward(-100);
        }
        if(isItemInArray(power, [mazeWorld.getX(), mazeWorld.getY()])){
            mazeWorld.insertReward(1);
        }
        if(isItemInArray(goal, [mazeWorld.getX(), mazeWorld.getY()])){
            mazeWorld.insertReward(100);
        }
        if(!isItemInArray(not_regular, [mazeWorld.getX(), mazeWorld.getY()])){
            mazeWorld.insertReward(-1);
        }
        bp.sync({ request: bp.Event("rewardUpdateDone"), block: MoveEventSet });
        bp.sync( {waitFor:bp.all} );
        bp.sync( {waitFor:bp.Event("updateDone")} );



    }
} );

bp.registerBThread( "terminate", function(){
    while (true){
        if(isItemInArray(terminate, [mazeWorld.getX(), mazeWorld.getY()])){
            bp.sync( {block:bp.all} );
        } else {
            bp.sync( {waitFor:bp.Event("rewardUpdateDone")} );
            bp.sync( {waitFor:MoveEventSet} );
            bp.sync( {waitFor:bp.Event("updateDone")} );
        }
    }
} );




