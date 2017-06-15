package com.iht.dataacquisition.core.common;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Page Object For Hibernate Query
 */
public class Page {

  // Common //
  public static final String ASC = "asc";

  public static final String DESC = "desc";

  // is auto count
  protected boolean autoCount = true;

  // desc or asc
  protected String order = null;

  // order by colum
  protected String orderBy = null;

  // pageNO //
  protected int pageNo = 1;

  // record number of per Page //
  protected int pageSize = 10;

  // result list
  protected List<?> result = Collections.emptyList();

  // total count
  protected long totalCount = -1;

  public Page() {
    super();
    pageSize = Constants.MAX_PAGE_ITEMS;
  }

  public Page(final int pageSize) {
    setPageSize(pageSize);
  }

  public Page(final int pageSize, final boolean autoCount) {
    setPageSize(pageSize);
    this.autoCount = autoCount;
  }

  public int getFirst() {
    return ((pageNo - 1) * pageSize) + 1;
  }

  public int getNextPage() {
    if (isHasNext()) {
      return pageNo + 1;
    } else {
      return pageNo;
    }
  }

  public String getOrder() {
    return order;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public int getPageNo() {
    return pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getPrePage() {
    if (isHasPre()) {
      return pageNo - 1;
    } else {
      return pageNo;
    }
  }

  public List<?> getResult() {
    return result;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public long getTotalPages() {
    if (totalCount < 0) {
      return -1;
    }

    long count = totalCount / pageSize;
    if (totalCount % pageSize > 0) {
      count++;
    }
    return count;
  }

  public boolean isAutoCount() {
    return autoCount;
  }

  public boolean isHasNext() {
    return (pageNo + 1 <= getTotalPages());
  }

  public boolean isHasPre() {
    return (pageNo - 1 >= 1);
  }

  public boolean isOrderBySetted() {
    return StringUtils.isNotBlank(orderBy);
  }

  public void setAutoCount(final boolean autoCount) {
    this.autoCount = autoCount;
  }

  public void setOrder(final String order) {

    String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
    for (String orderStr : orders) {
      if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
        throw new IllegalArgumentException(orderStr);
      }
    }

    this.order = StringUtils.lowerCase(order);
  }

  public void setOrderBy(final String orderBy) {
    this.orderBy = orderBy;
  }

  public void setPageNo(final int pageNo) {
    this.pageNo = pageNo;

    if (pageNo < 1) {
      this.pageNo = 1;
    }
  }

  public void setPageSize(final int pageSize) {
    this.pageSize = pageSize;

    if (pageSize < 1) {
      this.pageSize = 1;
    }
  }

  public void setResult(final List<?> result) {
    this.result = result;
  }

  public void setTotalCount(final long totalCount) {
    this.totalCount = totalCount;
  }
}
