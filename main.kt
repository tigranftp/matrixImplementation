package mmcs.assignment2

fun main() {
    //println("kek");
    var lock = createMatrix(3, 3, 0);
    var key = createMatrix(2, 2, 1);
    key[0,1] = 0
    key[1,0] = 0
    println(key)
    print(lock)
    print(canOpenLock(key,lock))
}