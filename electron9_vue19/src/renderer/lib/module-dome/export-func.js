import {defVarA, defVarB} from "./export-var";

export function exportFuncA(){
    return "call exportFuncA";
}

export function exportFuncB(){
    return "call exportFuncB";
}

export function exportFuncC(){
    return "call exportFuncC";
}


function exportFuncD(){
    return "call exportFuncD";
}

function exportFuncE(){
    return "call exportFuncE";
}

export {
    exportFuncD as exportFuncDNewName,
    exportFuncE
}

function exportFuncDefA(){
    return "call exportFuncDefA";
}

function exportFuncDefB(){
    return "call exportFuncDefB";
}

export default {
    exportFuncDefA,
    exportFuncDefB
}