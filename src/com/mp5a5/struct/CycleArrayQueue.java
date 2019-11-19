package com.mp5a5.struct;

/**
 * <p/>rear指向队列的最后一个元素的后一个位置，初始值为0。
 * <p/>front指向队列第一个元素，初始值为0。
 * <p/>队列满：(rear+1)%maxSize=front
 * <p/>队列空：rear==front
 * <p/>队列数据个数：(rear+maxSize-front)%maxSize
 */
public class CycleArrayQueue {

}
