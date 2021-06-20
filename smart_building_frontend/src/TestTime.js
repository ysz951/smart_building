const moment = require('moment-timezone');
let time = "2021-03-30 17:49:16";
const currentTime = moment().utc().format('YYYY-MM-DD HH:mm:ss')
const modifiedTime = moment(time).format('YYYY-MM-DD HH:mm:ss')

// console.log(currentTime);
// console.log(modifiedTime);
// console.log(currentTime > modifiedTime)
// console.log(moment(time, 'YYYY-MM-DD HH:mm:ss', "America/Chicago").utc())
// console.log(moment(moment(time).utc()).tz("America/Chicago"))

var cutoffString = '2021-03-30 17:49:16'; // in utc
var utcCutoff = moment.utc(cutoffString, 'YYYY-MM-DD HH:mm:ss');
var displayCutoff = utcCutoff.clone().local();
console.log(utcCutoff);
console.log(displayCutoff)