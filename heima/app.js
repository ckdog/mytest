let server = require('http');
let urls = require('url');
let qs = require('querystring')


var httpserver = server.createServer()
httpserver.on('request',function(req,res){

    let datas={
        name:'tom',
        age:15,
        sex:'boy'
    }
    
    let getdata = urls.parse(req.url,true);
    
    if(getdata.pathname == '/getscript'){
        let datas={
            name:'tom',
            age:15
        }
        // console.log(data.url+'-------------'+data.query+'------------');
        let scriptstr = `${getdata.query.callback}(${JSON.stringify(datas)})`
        // console.log(scriptstr);
        // res.write(scriptstr)
        res.end(scriptstr)
    }else{
        res.write('getdd()')
        res.end()
    }

    

}).listen(8080);