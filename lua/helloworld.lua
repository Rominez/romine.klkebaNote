#!/usr/local/bin/lua

-- print("hello world)

-----------------

--[[
function f (n) -- 定义函数
  if n > 0 then -- if 语句
    return 1
  else -- else 语句
    return 0
  end -- if else 结尾
end -- 函数结尾

a = io.read("*n") -- 输入数字
print(f(a))
]]

-----------------

--[[
function f (a)
  a = a or 1 -- a 为 nil 时的默认值，相当于 if not a then a = 1
  print(a)
  if a then -- 0 1 都会识别为 ture，只有 nil 会识别为 false
    return true
  else
    return false
  end
end

a = io.read("*n")
print(f(a))
--]]

-----------------------
