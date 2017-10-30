var express = require('express');
var path = require('path');
var mysql = require('mysql');
var app = express();
var bodyParser = require('body-parser');
var db = require('./config')
var multer  =   require('multer');
var storage =   multer.diskStorage({
  destination: function (req, file, callback) {
    callback(null, './uploads');
  },
  filename: function (req, file, callback) {
    callback(null, file.fieldname + '-' + Date.now());
  }
});
var upload = multer({ storage : storage}).single('userPhoto');


var connection = mysql.createConnection({
   host     : db.host,
   user     : db.user,
   password : db.password
});

connection.query('USE javabase');

app.set('port', 3000);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');
app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.get('/', function(req, res){
  connection.query('SELECT * FROM users', function(err, rows){
    res.json({users : rows});
  });
});
app.get('/register', function(req, res){
   res.sendFile(path.join(__dirname+'/public/register.html'));
});

app.post('/signup',function(req,res){
   var name=req.body.name;
   var password=req.body.password;
   var email=req.body.email;
 
   
   console.log("name= "+name+", password is "+password);
   connection.query('INSERT INTO users (name, password, email) VALUES ("'+ name + '","' + password + '","' + email + '")', function(err, rows) {
      res.send('done');
   });
});

app.post('/signupTeacher',function(req,res){
   var name=req.body.name;
   var password=req.body.password;
   var email=req.body.email;
 
   
   console.log("name= "+name+", password is "+password);
   connection.query('INSERT INTO teachers (name, password, email) VALUES ("'+ name + '","' + password + '","' + email + '")', function(err, rows) {
      res.send('done');
   });
});

app.post('/login',function(req,res){
   var username=req.body.name;
   var password=req.body.password;
   var email=req.body.email;
 
   
   console.log("name= "+name+", password is "+password);
   connection.query("SELECT * FROM users WHERE name='" + username + "' && password='" + password+ "'", function(err, rows) {
      res.send('done');
   });
});

app.listen(app.get('port'));
console.log('Express server listening on port ' + app.get('port'));
