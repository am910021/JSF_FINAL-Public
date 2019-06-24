/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Total seconds to wait

function beanStatusChange(selectObject) {
    $des = $("#beanDescriptionGroup");
    var value = selectObject.value;  
    if(value == 1){
        
        $des.show();
    }else{
        $des.hide();
    }
}

