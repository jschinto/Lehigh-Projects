var sumName = "";
var API_KEY = "17f064ea-9950-43db-b08e-5ec7c8451cd6";
var SUMMONER_NAME = "";
var SUMMONER_NAME_NOSPACES = "";
var summonerID = 0;
var time = 0;
var creep = 0;
var matchID = 0;
var selectedGame = 0;
var mode = 0;
var maxGame = 0;
var Nmatches = [];
var Rmatches = [];
var mins = [];
var cs = [];
var gold = [];
var compare = [];
var partId = 0;
var winn = "";

function summonerLookUp() {
    SUMMONER_NAME = $("#userName").val();

    if (SUMMONER_NAME !== "") {

        $.ajax({
            url: 'https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/' + SUMMONER_NAME + '?api_key=' + API_KEY,
            type: 'GET',
            dataType: 'json',
            data: {

            },
            success: function (json) {
                selectedGame=0;
                SUMMONER_NAME_NOSPACES = SUMMONER_NAME.replace(" ", "");

                SUMMONER_NAME_NOSPACES = SUMMONER_NAME_NOSPACES.toLowerCase().trim();

                var summonerLevel = json[SUMMONER_NAME_NOSPACES].summonerLevel;
                summonerID = json[SUMMONER_NAME_NOSPACES].id;

                document.getElementById("sLevel").innerHTML = summonerLevel;
                document.getElementById("sID").innerHTML = summonerID;
                document.getElementById("name").innerHTML = SUMMONER_NAME;
                
                sumName = json[SUMMONER_NAME_NOSPACES].name;
                getGame();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error getting Summoner data!");
            }//http://cors.io/?u=
        });
    } else {}
}
Date.prototype.formatMMDDYYYY = function(){
    return (this.getMonth() + 1) + 
    "/" +  this.getDate() +
    "/" +  this.getFullYear();
}

function getGame(){
    $.ajax({
            url: 'https://na.api.pvp.net/api/lol/na/v1.3/game/by-summoner/' + summonerID + '/recent?api_key=' + API_KEY,
            type: 'GET',
            dataType: 'json',
            data: {

            },
            success: function (json) {
                Nmatches = json.games;
                matchID = json.games[selectedGame].gameId;

                document.getElementById("mID").innerHTML = matchID;
                if(Nmatches[selectedGame].subType!=='NONE'){
                document.getElementById("queue").innerHTML = Nmatches[selectedGame].subType;
            }
            else{
                document.getElementById("queue").innerHTML = Nmatches[selectedGame].gameType;
            }
                document.getElementById("season").innerHTML = new Date(Nmatches[selectedGame].createDate).formatMMDDYYYY();
                champion(Nmatches[selectedGame].championId);
                getRanked();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error getting Summoner data!");
            }
        });
}
function getRanked(){
    $.ajax({
            url: 'https://na.api.pvp.net/api/lol/na/v2.2/matchlist/by-summoner/' + summonerID + '?api_key=' + API_KEY,
            type: 'GET',
            dataType: 'json',
            data: {

            },
            success: function (json) {
                Rmatches = json.matches;
                maxGame = json.totalGames;
                maxGame--;
                mode=1;
                modes();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error getting Summoner data!");
            }
        });
}
function champion(champid){
    $.ajax({
            url: 'https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/'+champid+'?champData=tags&api_key=' + API_KEY,
            type: 'GET',
            dataType: 'json',
            data: {

            },
            success: function (json) {
                
                document.getElementById("champ").innerHTML = json.name;
                
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error getting Summoner data!");
            }
        });
}

function Name() {
    alert(sumName);
}
function next(direct){
    if (matchID !== 0){
        if(direct==true&&selectedGame!==Nmatches.length-1&&mode==0){
            selectedGame++;
            document.getElementById("show").innerHTML = '<button type="button" class="btn btn-primary" id="showButton" onclick="begin()">Show Details</button>';
            matchID=Nmatches[selectedGame].gameId;
            document.getElementById("mID").innerHTML = matchID;
            if(Nmatches[selectedGame].subType!=='NONE'){
                document.getElementById("queue").innerHTML = Nmatches[selectedGame].subType;
            }
            else{
                document.getElementById("queue").innerHTML = Nmatches[selectedGame].gameType;
            }
            document.getElementById("season").innerHTML = new Date(Nmatches[selectedGame].createDate).formatMMDDYYYY();
            champion(Nmatches[selectedGame].championId);
        }
        else if(direct==false&&selectedGame!==0&&mode==0){
            selectedGame--;
            document.getElementById("show").innerHTML = '<button type="button" class="btn btn-primary" id="showButton" onclick="begin()">Show Details</button>';
            matchID=Nmatches[selectedGame].gameId;
            document.getElementById("mID").innerHTML = matchID;
            if(Nmatches[selectedGame].subType!=='NONE'){
                document.getElementById("queue").innerHTML = Nmatches[selectedGame].subType;
            }
            else{
                document.getElementById("queue").innerHTML = Nmatches[selectedGame].gameType;
            }
            document.getElementById("season").innerHTML = new Date(Nmatches[selectedGame].createDate).formatMMDDYYYY();
            champion(Nmatches[selectedGame].championId);
        }
        else if(direct==true&&selectedGame==Nmatches.length-1&&mode==0){
            alert("No more saved data.  Switch to Ranked list to see more.");
        }
        else if(direct==false&&selectedGame==0){
            alert("No new games!");
        }
        else if(direct==true&&selectedGame!==maxGame&&mode==1){
            selectedGame++;
            document.getElementById("show").innerHTML = '<button type="button" class="btn btn-primary" id="showButton" onclick="begin()">Show Details</button>';
            matchID=Rmatches[selectedGame].matchId;
            document.getElementById("mID").innerHTML = matchID;
            document.getElementById("queue").innerHTML = Rmatches[selectedGame].queue;
            document.getElementById("season").innerHTML = new Date(Rmatches[selectedGame].timestamp).formatMMDDYYYY();
            champion(Rmatches[selectedGame].champion);
        }
        else if(direct==false&&selectedGame!==0&&mode==1){
            selectedGame--;
            document.getElementById("show").innerHTML = '<button type="button" class="btn btn-primary" id="showButton" onclick="begin()">Show Details</button>';
            matchID=Rmatches[selectedGame].matchId;
            document.getElementById("mID").innerHTML = matchID;
            document.getElementById("queue").innerHTML = Rmatches[selectedGame].queue;
            document.getElementById("season").innerHTML = new Date(Rmatches[selectedGame].timestamp).formatMMDDYYYY();
            champion(Rmatches[selectedGame].champion);
        }
        else if(direct==true&&selectedGame==maxGame&&mode==1){
            alert("No more saved data!");
        }
    }
}
function modes(){
    if(mode==0){
        selectedGame=-1;
        mode=1;
        document.getElementById("mode").innerHTML = "Mode: Ranked Games";
        if(maxGame==-1){
            alert("No Ranked Games!");
            modes();
        }
        else {
            next(true);
        }
    }
    else{
        selectedGame=-1;
        mode=0;
        document.getElementById("mode").innerHTML = "Mode: Recent Games";
        next(true);
    }
}
function begin(){
    if (sumName !== "") {

        $.ajax({
            url: 'https://na.api.pvp.net/api/lol/na/v2.2/match/' + matchID + '?includeTimeline=true&api_key=' + API_KEY,
            type: 'GET',
            dataType: 'json',
            data: {

            },
            success: function (json) {
                compare = [];
                mins = [];
                cs = [];
                gold = [];
                if(mode==0){//riot tries to hide identities in normals, so I had to compare stats to find it
                    compare[0] = Nmatches[selectedGame].teamId;
                    compare[1] = Nmatches[selectedGame].championId;
                    compare[2] = Nmatches[selectedGame].spell1;
                    compare[3] = Nmatches[selectedGame].spell2;
                    compare[4] = Nmatches[selectedGame].stats.totalDamageDealt;
                    compare[5] = Nmatches[selectedGame].stats.minionsKilled;
                    for(var x = 0;x<json.participants.length;x++){
                        if(compare.toString()==[json.participants[x].teamId,json.participants[x].championId,json.participants[x].spell1Id,json.participants[x].spell2Id,json.participants[x].stats.totalDamageDealt,json.participants[x].stats.minionsKilled].toString()){
                            partId = json.participants[x].participantId;
                        }
                    }
                }
                else if(mode==1){
                    for(var x = 0;x<json.participantIdentities.length;x++){
                        if(summonerID==json.participantIdentities[x].player.summonerId){
                            partId = json.participantIdentities[x].participantId;
                        }
                    }
                }
                cs[0] = 0;
                gold[0] = 0;
                if(json.participants[partId-1].stats.winner==true){
                    winn = "Win";
                }
                else if(json.participants[partId-1].stats.winner==false){
                    winn = "Loss";
                }
                document.getElementById("show").innerHTML = '<pre>Result: '+winn+'      Kills: '+json.participants[partId-1].stats.kills+'      Deaths: '+json.participants[partId-1].stats.deaths+'      Assists: '+json.participants[partId-1].stats.assists+'      CS: '+(json.participants[partId-1].stats.minionsKilled + json.participants[partId-1].stats.neutralMinionsKilled)+'</pre><div id="linechart"></div>';
                for(var x = 0;x<json.timeline.frames.length;x++){
                    mins[x] = x;
                    if(x!==0){
                        cs[x] = (json.timeline.frames[x].participantFrames[partId].minionsKilled + json.timeline.frames[x].participantFrames[partId].jungleMinionsKilled) - (json.timeline.frames[x-1].participantFrames[partId].minionsKilled + json.timeline.frames[x-1].participantFrames[partId].jungleMinionsKilled);
                    }
                    if(x!==0){
                        gold[x] = Math.round(((json.timeline.frames[x].participantFrames[partId].totalGold - json.timeline.frames[x-1].participantFrames[partId].totalGold)/60)*10)/10;
                    }
                }
                drawChart(mins,cs,gold);

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                
            }
        });
    }
}