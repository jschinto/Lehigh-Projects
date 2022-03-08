let fs = require('fs');
let lines = fs.readFileSync('zip_codes.csv').toString().split("\n");
for(let i=0; i < lines.length-1; ++i) {
    let line = lines[i];
    line = line.substring(0,line.length-1);
    let fields = line.split(",");
    let out = `zips["${fields[0]}"]={zip:"${fields[0]}",city:"${fields[4]}",state:"${fields[5]}",stateCode:"${fields[1]}",latitude:"${fields[2]}",longitude:"${fields[3]}"};`;
    process.stdout.write(out + "\n");
}