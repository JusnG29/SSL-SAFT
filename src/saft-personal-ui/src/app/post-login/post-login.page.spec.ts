import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PostLoginPage } from './post-login.page';

describe('PostLoginPage', () => {
  let component: PostLoginPage;
  let fixture: ComponentFixture<PostLoginPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [PostLoginPage],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostLoginPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
