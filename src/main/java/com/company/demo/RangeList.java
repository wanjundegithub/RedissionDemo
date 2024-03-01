package com.company.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author user
 */
@Slf4j
public class RangeList {

  /** record the start&end data structure */
  public static class Range {

    private static final String LEFT = "[";

    private static final String RIGHT = ")";

    private static final String COMMA_SEPARATED = ", ";

    private int start;

    private int end;

    public Range() {}

    public Range(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public int getStart() {
      return start;
    }

    public void setStart(int start) {
      this.start = start;
    }

    public int getEnd() {
      return end;
    }

    public void setEnd(int end) {
      this.end = end;
    }

    @Override
    public String toString() {
      return LEFT + start + COMMA_SEPARATED + end + RIGHT;
    }
  }

  /** valid input source */
  private static final int VALID_RANGE_SIZE = 2;

  private static final String EMPTY = "";

  private static final String SPACE = " ";

  /** range list data structure */
  private List<Range> rangeList;

  public RangeList(List<Range> rangeList) {
    this.rangeList = rangeList;
  }

  public RangeList() {
    this.rangeList = new ArrayList<>();
  }

  /**
   * Adds a range to the list
   *
   * @param {Array<number>} range - start/end
   */
  public void add(int start, int end) {
    add(new int[] {start, end});
  }

  /**
   * Adds a range to the list
   *
   * @param {Array<number>} range - Array of two integers that specify beginning and end of range.
   */
  public void add(int[] inputRange) {
    if (!checkParams(inputRange)) {
      return;
    }
    int start = inputRange[0];
    int end = inputRange[1];
    if (rangeList.isEmpty()) {
      rangeList.add(new Range(start, end));
    }
    List<Range> res = new ArrayList<>(rangeList.size() + 1);
    boolean flag = false;
    for (Range range : rangeList) {
      int curStart = range.getStart();
      int curEnd = range.getEnd();
      // not intersect eg:inputRange:[3, 6] , current range:[1, 2]
      if (curEnd < start) {
        res.add(range);
        continue;
      }
      // not intersect eg:inputRange:[3, 6] , current range:[7, 10]
      if (curStart > end) {
        if (!flag) {
          res.add(new Range(start, end));
          flag = true;
        }
        res.add(range);
        continue;
      }
      // coverage interval eg:inputRange:[3, 6] , current range:[4, 5]
      if (curStart > start && curEnd < end) {
        continue;
      }
      //  新增区间前半部分与遍历的节点item相交，扩展新增区间start的范围至item[0]
      //            // 新增区间(原来) = [5,10)
      //            // 遍历节点item = [4, 6)
      //            // 新增区间(更新后) = [4,10)
      if (curStart < start) {
        start = curStart;
      }
      // 3.3 新增区间后半部分与遍历的节点item相交，扩充新增区间end的范围至item[1]
      // 新增区间(原来) = [5,10)
      // 遍历节点item = [8, 12)
      // 新增区间(更新后) = [5,12)
      if (curEnd > end) {
        end = curEnd;
      }
    }
    // 4 边界处理，可能需要把新增区间插入到结果列表中
    Range lastRange = res.size() == 0 ? new Range(start, end) : res.get(res.size() - 1);
    if (res.size() == 0 || lastRange.getEnd() < start) {
      res.add(new Range(start, end));
    }
    rangeList = res;
  }

  /**
   * Removes a range from the list
   *
   * @param {Array<number>} range - start end
   */
  public void remove(int start, int end) {
    remove(new int[] {start, end});
  }

  /**
   * Removes a range from the list
   *
   * @param {Array<number>} range - Array of two integers that specify beginning and end of range.
   */
  public void remove(int[] inputRange) {
    if (!checkParams(inputRange)) {
      return;
    }
    if (rangeList.isEmpty()) {
      return;
    }
    int start = inputRange[0];
    int end = inputRange[1];
    List<Range> res = new ArrayList<>(rangeList.size() + 1);
    for (Range range : rangeList) {
      int curStart = range.getStart();
      int curEnd = range.getEnd();
      // 1 不相交，直接加入到结果集中
      // 移除区间range = [10,12)
      // 遍历节点item = [6,8) 或 [14,15)
      if (start >= curEnd || end <= curStart) {
        res.add(range);
        continue;
      }
      // 2 以下是相交的情况
      // 2.1 完全移除：移除区间range完全包含或覆盖遍历的节点item区间
      // 移除区间range = [5,10)
      if (start <= curStart && end >= curEnd) {
        continue;
      }
      // 移除部分区间
      if (start <= curEnd) {
        if (end >= curEnd) {
          // 2.2 移除后部分：移除区间range前部与遍历的节点item后部相交，缩短遍历节点item的end范围至range[0]
          // 移除区间range =      [5,10)
          // 遍历节点item =       [4,6)
          // 遍历节点item(更新后) = [4,5)
          curEnd = start;
          range.setEnd(curEnd);
          res.add(range);
        } else {
          // 2.3 移除中间部分：原来区间拆分成[item[0], range[0])与[range[1], item[1]) 2部分
          // 移除区间range =      [5,10)
          // 遍历节点item =          [4,10)
          // 遍历节点item(更新后) =   [7,8)
          // 遍历节点拆分成2部分 =     [4,7) [8,10)
          if (start > curStart) {
            res.add(new Range(curStart, start));
          }
          res.add(new Range(end, curEnd));
        }
        continue;
      }
      // 2.4 移除前部分：移除区间range后部分与遍历的节点item前部相交，缩短遍历节点start范围至range[1]
      // 移除区间range =      [5,10)
      // 遍历节点item =       [8,12)
      // 遍历节点item(更新后)= [10,12)
      if (end > curStart) {
        curStart = end;
        range.setStart(curStart);
        res.add(range);
      }
    }
    rangeList = res;
  }

  /**
   * Convert the list of ranges in the range list to a string
   *
   * @returns A string representation of the range list
   */
  @Override
  public String toString() {
    if (rangeList.isEmpty()) {
      return EMPTY;
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (int index = 0; index < rangeList.size(); index++) {
      Range range = rangeList.get(index);
      stringBuilder.append(range.toString());
      if (index < rangeList.size() - 1) {
        stringBuilder.append(SPACE);
      }
    }
    return stringBuilder.toString();
  }

  /**
   * param validation
   *
   * @param range array interval
   * @return boolean true=verification passed，false=verification failed
   */
  private boolean checkParams(int[] range) {
    // parameter and length verification
    if (range == null || range.length != VALID_RANGE_SIZE) {
      log.error("range is empty or range length not equal 2");
      return false;
    }
    // parameter value verification
    if (range[0] > range[1]) {
      log.error("start:{} is more than end:{}", range[0], range[1]);
      return false;
    }
    return true;
  }
}
