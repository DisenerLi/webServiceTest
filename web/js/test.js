/**
 * Created by liqiang on 2017/4/7.
 */

(function(){

    var i = 1, j = 10;
    do{
        if(++i>--j);

    }while(i<5);
    console.log('i === '+i);
    console.log('j === '+j);

}());
