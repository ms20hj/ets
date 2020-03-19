export function sliceArray(list, subLength) {
  let result = [];
  for(let i=0,len=list.length; i<len; i+=subLength){
    result.push(list.slice(i,i+subLength));
  }
  return result;
}
