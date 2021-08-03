export var exportVarA="aaaa";
export var exportVarB="bbbb";
export var exportVarC="cccc";

var exportVarD="dddd";
var exportVarE="eeee";

export {
    exportVarD as exportVarDNewName,
    exportVarE
}

export var defVarA="def-aaaa";
export var defVarB="def-bbbb";

export default {
    defVarA,
    defVarB
}