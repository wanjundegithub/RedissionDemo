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
      // not intersect eg:current range:[3, 6) , input range:[7, 10)
      if (curEnd < start) {
        res.add(range);
        continue;
      }
      // not intersect eg:current range: [3, 6) ,input range:[1, 2)
      if (curStart > end) {
        if (!flag) {
          res.add(new Range(start, end));
          flag = true;
        }
        res.add(range);
        continue;
      }
      // coverage interval eg:current range:[3, 6) , input range:[2, 8)
      if (curStart > start && curEnd < end) {
        continue;
      }
      // intersect eg:current range:[3,6) input range:[4,7) expand input range
      // new input range:[4, 7) -> [3,7)
      if (curStart < start) {
        start = curStart;
      }
      // intersect eg:current range:[3,6) input range:[2,5) expand input range
      // new input range:[2,5) -> [2,6)
      if (curEnd > end) {
        end = curEnd;
      }
    }
    // handle boundary, it may be necessary to insert the new interval into the result list
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
      //not intersect,eg:curRange:[3,6) inputRange [1,3) or [6,8)
      if (start >= curEnd || end <= curStart) {
        res.add(range);
        continue;
      }
      // coverage interval eg:current range:[3, 6) , input range:[2, 8)
      // remove current range from range list
      if (start <= curStart && end >= curEnd) {
        continue;
      }
      // remove sub range
      if (start <= curEnd) {
        if (end >= curEnd) {
          //shrink current range eg: current range:[3,6) input range [4,7)
          // current range [3,6)->[3,4)
          curEnd = start;
          range.setEnd(curEnd);
          res.add(range);
        } else {
          //split current range,eg: current range:[3,6) input range [4,5)
          //current range [3,6) -> [3,4) and [5,6)
          //when input range [2,5)
          //current range [3,6) -> [5,6)
          if (start > curStart) {
            res.add(new Range(curStart, start));
          }
          res.add(new Range(end, curEnd));
        }
        continue;
      }
      //shrink rang,eg:current range:[3,6) input range [1,4)
      //current range [3,6) -> [4,6)
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
